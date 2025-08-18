package com.nutrisport.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import com.nutrisport.auth.component.GoogleButton
import com.nutrisport.shared.Alpha
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.Surface
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.TextSecondary
import com.nutrisport.shared.UbuntuMediumFont
import com.nutrisport.shared.UbuntuRegularFont
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@Composable
fun AuthScreen(
    navigateToHomeScreen : () -> Unit
){

    val viewModel = koinViewModel<AuthViewModel>()

    val messageBarState = rememberMessageBarState()
    var loadingState by remember { mutableStateOf(false) }


    Scaffold() { paddingValues ->

        ContentWithMessageBar(
            modifier = Modifier
                .padding(paddingValues),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            contentBackgroundColor = Surface
        ){

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)){

                Column(
                    modifier = Modifier.align(alignment = Alignment.Center)
                ) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "NUTRISPORT",
                        textAlign = TextAlign.Center,
                        fontFamily = UbuntuMediumFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(Alpha.HALF),
                        text = "Sign in to continue",
                        textAlign = TextAlign.Center,
                        fontFamily = UbuntuRegularFont(),
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary
                    )

                }

                GoogleButtonUiContainerFirebase(
                    modifier = Modifier.fillMaxSize(),
                    onResult = { result ->
                        result.onSuccess { user->
                            viewModel.createCustomer(
                                user = user,
                                onSuccess = {messageBarState.addSuccess("Authentication Successful")},
                                onError = { message->
                                    messageBarState.addError(message)
                                }
                            )
                            navigateToHomeScreen()
                        }.onFailure { error ->
                            messageBarState.addError("${error.message}")
                        }
                        loadingState = false
                    },
                ) {
                    GoogleButton(
                        modifier = Modifier.fillMaxWidth().align(alignment = Alignment.BottomStart),
                        isLoading = loadingState,
                        onCLick = {
                            loadingState = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )

                }


            }

        }

    }

}

@Preview
@Composable
fun Prev(){
    AuthScreen(
        navigateToHomeScreen = {

        }
    )
}