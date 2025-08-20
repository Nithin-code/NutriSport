package com.nutrisport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nithin.home.HomeGraph
import com.nutrisport.auth.AuthScreen
import com.nutrisport.shared.Screen

@Composable
fun SetUpNavGraph(
    startDestination : Screen = Screen.Auth
){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable<Screen.Auth> {
            AuthScreen(
                navigateToHomeScreen = {
                    navController.navigate(Screen.HomeGraph){
                       popUpTo<Screen.Auth> { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.HomeGraph> {
            HomeGraph()
        }
    }

}