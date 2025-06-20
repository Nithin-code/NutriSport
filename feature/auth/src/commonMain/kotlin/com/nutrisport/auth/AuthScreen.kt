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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nutrisport.auth.component.GoogleButton
import com.nutrisport.shared.Alpha
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.TextSecondary
import com.nutrisport.shared.UbuntuMediumFont
import com.nutrisport.shared.UbuntuRegularFont
import rememberMessageBarState

@Composable
fun AuthScreen(

){
    val messageBarState = rememberMessageBarState()

    Scaffold() { paddingValues ->

        ContentWithMessageBar(
            modifier = Modifier
                .padding(paddingValues),
            messageBarState = messageBarState,
            errorMaxLines = 2
        ){

            Box(modifier = Modifier.fillMaxSize()){

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

                GoogleButton(
                    modifier = Modifier.fillMaxWidth().align(alignment = Alignment.BottomStart)
                        .padding(10.dp),
                    isLoading = false,
                    onCLick = {

                    }
                )

            }

        }

    }

}