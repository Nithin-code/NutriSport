package com.nithin.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Surface
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.UbuntuMediumFont
import com.nutrisport.shared.components.CustomButton
import com.nutrisport.shared.components.ProfileForm
import com.nutrisport.shared.domain.Country
import com.nutrisport.shared.utils.DisplayResult
import com.nutrisport.shared.utils.RequestState
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack : () -> Unit
){

    val viewModel = koinViewModel<ProfileViewModel>()

    val screenState = viewModel.screenState

    var country by remember {
        mutableStateOf(Country.INDIA)
    }

    Scaffold(
        containerColor = Surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Profile",
                        fontFamily = UbuntuMediumFont(),
                        fontSize = FontSize.LARGE,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack
                    ) {
                        Icon(
                            painter = painterResource(Resources.Icon.BackArrow) ,
                            contentDescription = "Back Icon",
                            tint = IconPrimary,
                        )
                    }

                }
            )
        }
    ) { padding->
        Column(
            modifier = Modifier
                .padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding())
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {


            screenState.DisplayResult(
                onLoading = {

                },
                onSuccess = {state->
                    ProfileForm(
                        modifier = Modifier.weight(1f),
                        firstName = state.firstName,
                        onFirstNameChanged = {},
                        lastName = state.lastName,
                        onLastNameChanged = {},
                        email = state.email,
                        city = state.city,
                        onCityChanged = {},
                        postalCode = ,
                        onPostalCodeChanged = {},
                        address = "",
                        onAddressChanged = {},
                        phoneNumber = "",
                        onPhoneNumberChanged = {},
                        country = state.country,
                        onCountrySelect = { it->
                            country = it
                        }
                    )
                },
                onError = {

                },
                onIdle = {

                }
            )




            CustomButton(
                modifier = Modifier,
                text = "Update",
                icon = Resources.Icon.Checkmark,
                onClick = navigateBack
            )
        }
    }


}