package com.nithin.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nithin.home.domain.BottomBarDestination
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Screen
import com.nutrisport.shared.Surface
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.UbuntuMediumFont
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraph() {

    val navController = rememberNavController()

//    var selectedDestination by remember {
//        mutableStateOf(BottomBarDestination.PRODUCT_OVERVIEW)
//    }

    val currentRoute = navController.currentBackStackEntryAsState()

    val selectedDestination = remember {
        derivedStateOf {
            val route = currentRoute.value?.destination?.route.toString()
            when {
                route.contains(BottomBarDestination.PRODUCT_OVERVIEW.screen.toString()) -> BottomBarDestination.PRODUCT_OVERVIEW
                route.contains(BottomBarDestination.Cart.screen.toString()) -> BottomBarDestination.Cart
                route.contains(BottomBarDestination.Categories.screen.toString()) -> BottomBarDestination.Categories
                else -> BottomBarDestination.PRODUCT_OVERVIEW
            }
        }
    }


    Scaffold(
        containerColor = Surface,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AnimatedContent(
                        targetState = selectedDestination.value
                    ){ destination ->

                        Text(
                            text = destination.title,
                            fontFamily = UbuntuMediumFont(),
                            fontSize = FontSize.LARGE,
                            color = TextPrimary
                        )

                    }
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(Resources.Icon.Menu),
                        contentDescription = "Menu Icon",
                        tint = IconPrimary
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = Screen.ProductOverview
            ) {
                composable<Screen.ProductOverview> { }
                composable<Screen.Cart> { }
                composable<Screen.Catagories> { }
            }

            Spacer(modifier = Modifier.height(12.dp))

            BottomBar(
                modifier = Modifier,
                selected = selectedDestination.value,
                onSelected = { destination ->
                    navController.navigate(destination.screen) {
                        launchSingleTop = true
                        popUpTo<Screen.ProductOverview> {
                            saveState = true
                            inclusive = false
                        }
                        restoreState = true
                    }
                }
            )

        }
    }

}