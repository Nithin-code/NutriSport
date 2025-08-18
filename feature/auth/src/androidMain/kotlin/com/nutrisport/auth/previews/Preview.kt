package com.nutrisport.auth.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nutrisport.auth.AuthScreen
import com.nutrisport.auth.component.GoogleButton

@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun Preview(){
    AuthScreen(
        navigateToHomeScreen = {

        }
    )
}