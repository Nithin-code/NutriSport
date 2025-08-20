package com.nithin.nutrisport

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.nithin.data.domain.CustomerRepository
import com.nutrisport.shared.Screen
import com.nutrisport.navigation.SetUpNavGraph
import com.nutrisport.shared.Constants
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import nutrisport.composeapp.generated.resources.Res
import nutrisport.composeapp.generated.resources.compose_multiplatform
import org.koin.compose.koinInject

@Composable
@Preview
fun App(
    modifier: Modifier = Modifier
) {
    MaterialTheme {

        val customerRepository = koinInject<CustomerRepository>()

        var isAppReady by remember { mutableStateOf(false) }


        val isUserAuthenticated = remember {
            customerRepository.getUserId() != null
        }

        val startDestination = remember {
            if (isUserAuthenticated) Screen.HomeGraph else Screen.Auth
        }

        LaunchedEffect(Unit) {
            GoogleAuthProvider.create(
                credentials = GoogleAuthCredentials(serverId = Constants.WEB_CLIENT_ID)
            )
            isAppReady = true
        }
        AnimatedVisibility(
            modifier = modifier,
            visible = isAppReady
        ) {
            SetUpNavGraph(
                startDestination = Screen.HomeGraph
            )
        }

    }
}