package com.nithin.profile

import ContentWithMessageBar
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
import androidx.compose.runtime.LaunchedEffect
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
import com.nutrisport.shared.components.ErrorCard
import com.nutrisport.shared.components.InfoCard
import com.nutrisport.shared.components.LoadingCard
import com.nutrisport.shared.components.ProfileForm
import com.nutrisport.shared.domain.Country
import com.nutrisport.shared.utils.DisplayResult
import com.nutrisport.shared.utils.RequestState
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack : () -> Unit
){
    val viewModel = koinViewModel<ProfileViewModel>()
    val screenState = viewModel.screenState
    val screenReady= viewModel.isScreenReady
    val isFormValid = viewModel.isFormValid()

    val messageBarState = rememberMessageBarState()


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
        ContentWithMessageBar(
            modifier = Modifier.padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding()),
            contentBackgroundColor = Surface,
            messageBarState = messageBarState,
            errorMaxLines = 2
        ){

            Column(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {


                screenReady.DisplayResult(
                    onLoading = {
                        LoadingCard(modifier = Modifier.fillMaxSize())
                    },
                    onSuccess = { state ->
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            ProfileForm(
                                modifier = Modifier.weight(1f),
                                firstName = screenState.firstName,
                                onFirstNameChanged = {
                                    viewModel.updateItem(ProfileScreenEvent.UpdateFirstName(value = it))
                                },
                                lastName = screenState.lastName,
                                onLastNameChanged = {
                                    viewModel.updateItem(ProfileScreenEvent.UpdateLastName(value = it))
                                },
                                email = screenState.email,
                                city = screenState.city,
                                onCityChanged = {
                                    viewModel.updateItem(ProfileScreenEvent.UpdateCity(value = it))
                                },
                                postalCode = screenState.postalCode,
                                onPostalCodeChanged = {
                                    viewModel.updateItem(ProfileScreenEvent.UpdatePostalCode(value = it?:""))
                                },
                                address = screenState.address ?: "",
                                onAddressChanged = { viewModel.updateItem(ProfileScreenEvent.UpdateAddress(value = it))},
                                phoneNumber = screenState.mobileNo?.number,
                                onPhoneNumberChanged = { viewModel.updateItem(ProfileScreenEvent.UpdateMobileNo(value = it))},
                                country = screenState.country,
                                onCountrySelect = { it ->

                                }
                            )

                            CustomButton(
                                modifier = Modifier,
                                text = "Update",
                                icon = Resources.Icon.Checkmark,
                                enabled = isFormValid,
                                onClick = {
                                    viewModel.updateItem(
                                        ProfileScreenEvent.UpdateBtnClick(
                                            onError = {message->
                                                messageBarState.addError(message)
                                            },
                                            onSuccess = {
                                                messageBarState.addSuccess("Successfully Updated..")
                                            }
                                        )
                                    )
                                }
                            )

                        }
                    },
                    onError = { message ->
                        InfoCard(
                            modifier = Modifier,
                            image = Resources.Image.Cat,
                            title = "Oops!",
                            subTitle = message
                        )
                    },
                    onIdle = {},
                    backgroundColor = Surface
                )

            }

        }
    }


}