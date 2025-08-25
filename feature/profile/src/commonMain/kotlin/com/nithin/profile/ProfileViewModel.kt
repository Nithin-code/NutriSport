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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


data class ProfileScreenState(
    val firstName : String = "",
    val lastName : String = "",
    val email : String = "",
    val city : String? = null,
    val postalCode : String? = null,
    val address : String? = null,
    val mobileNo : PhoneNumber? = null,
    val country: Country = Country.INDIA
)

class ProfileViewModel(
    private val customerRepository: CustomerRepository
) : ViewModel(){

    private val customer = customerRepository
        .readCustomerFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RequestState.Loading
        )


    var screenState : RequestState<ProfileScreenState> by mutableStateOf(RequestState.Loading)

    init {
        viewModelScope.launch {
            customer.collectLatest { data->
                if (data.isSuccess()){
                    val fetchedDta = data.getSuccessData()
                    screenState = RequestState.Success(
                        data = fetchedDta.toScreenStateData()
                    )
                }else if (data.isError()){
                    screenState = RequestState.Error(message = data.getErrorMessage())
                }
            }
        }
    }

}

fun Customer.toScreenStateData() : ProfileScreenState{
    return ProfileScreenState(
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