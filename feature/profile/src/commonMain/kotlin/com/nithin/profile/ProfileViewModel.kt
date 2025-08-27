package com.nithin.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithin.data.domain.CustomerRepository
import com.nutrisport.shared.domain.Country
import com.nutrisport.shared.domain.Customer
import com.nutrisport.shared.domain.PhoneNumber
import com.nutrisport.shared.utils.RequestState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


data class ProfileScreenState(
    val id : String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val city: String? = null,
    val postalCode: String? = null,
    val address: String? = null,
    val mobileNo: PhoneNumber? = null,
    val country: Country = Country.INDIA
)

class ProfileViewModel(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    private val customer = customerRepository
        .readCustomerFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RequestState.Loading
        )


    var screenState: ProfileScreenState by mutableStateOf(ProfileScreenState())

    var isScreenReady: RequestState<Unit> by mutableStateOf(RequestState.Loading)

    init {
        viewModelScope.launch {
            customer.collectLatest { data ->
                if (data.isSuccess()) {
                    val fetchedDta = data.getSuccessData()
                    screenState = fetchedDta.toScreenStateData()
                    isScreenReady = RequestState.Success(Unit)
                } else if (data.isError()) {
                    isScreenReady = RequestState.Error(data.getErrorMessage())
                }
            }
        }
    }

    fun updateCustomer(
        onSuccess : () -> Unit,
        onError : (String) -> Unit
    ){
        viewModelScope.launch {
            customerRepository.updateCustomer(
                customer = Customer(
                    id = screenState.id,
                    firstName = screenState.firstName,
                    lastName = screenState.lastName,
                    email = screenState.email,
                    postalCode = screenState.postalCode,
                    mobileNo = screenState.mobileNo,
                    address = screenState.address,
                    city = screenState.city
                ),
                onSuccess = onSuccess,
                onError = onError
            )
        }
    }

    fun updateItem(profileScreenEvent: ProfileScreenEvent) {

        when (profileScreenEvent) {
            is ProfileScreenEvent.UpdateAddress -> {
                screenState = screenState.copy(address = profileScreenEvent.value)
            }

            is ProfileScreenEvent.UpdateCity -> {
                screenState = screenState.copy(city = profileScreenEvent.value)
            }

            is ProfileScreenEvent.UpdateFirstName -> {
                screenState = screenState.copy(firstName = profileScreenEvent.value)
            }

            is ProfileScreenEvent.UpdateLastName -> {
                screenState = screenState.copy(lastName = profileScreenEvent.value)
            }

            is ProfileScreenEvent.UpdateMobileNo -> {
                screenState = screenState.copy(
                    mobileNo = PhoneNumber(
                        dailCode = screenState.country.dailCode,
                        number = profileScreenEvent.value
                    )
                )
            }

            is ProfileScreenEvent.UpdatePostalCode -> {
                screenState = screenState.copy(postalCode = profileScreenEvent.value)
            }

            is ProfileScreenEvent.UpdateBtnClick -> {
                updateCustomer(
                    onSuccess = profileScreenEvent.onSuccess,
                    onError = profileScreenEvent.onError
                )
            }
        }

    }

    fun isFormValid() = with(screenState) {
        firstName.length in 3..50
                && lastName.length in 3..50
                && city?.length in 3..50
                && postalCode?.toString()?.length in 3..8
                && address?.length in 3..50
                && mobileNo?.number?.length in 5..30

    }

}




fun Customer.toScreenStateData(): ProfileScreenState {
    return ProfileScreenState(
        id = this.id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        postalCode = postalCode,
        address = address,
        city = city,
        mobileNo = mobileNo,
        country = Country.entries.firstOrNull {
            it.dailCode == mobileNo?.dailCode
        } ?: Country.INDIA
    )
}