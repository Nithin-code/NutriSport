package com.nithin.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nithin.home.components.BottomBar
import com.nithin.home.components.CustomDrawer
import com.nithin.home.domain.BottomBarDestination
import com.nithin.home.domain.DrawerState
import com.nithin.home.domain.MakeOpposite
import com.nithin.home.domain.isOpened
import com.nithin.home.viewmodel.HomeGraphViewModel
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Screen
import com.nutrisport.shared.Surface
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.UbuntuMediumFont
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraph(
    navigateToAuthScreen : () -> Unit,
    navigateToProfileScreen : () -> Unit
) {

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

    var drawerState by remember {
        mutableStateOf(DrawerState.CLOSED)
    }

    val screenPosition = animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 300f else 0f,
        animationSpec = tween(durationMillis = 600)
    )

    val scale = animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.8f else 1f,
        animationSpec = tween(durationMillis = 600)
    )

    val viewModel = koinViewModel<HomeGraphViewModel>()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
    ){

        Scaffold(

        ) { padding->
            CustomDrawer(
                padding,
                onSignOutClicked = {
                    viewModel.signOutUser(
                        onError = { message->

                        },
                        onSuccess = {
                            navigateToAuthScreen.invoke()
                        }
                    )
                },
                onProfileClicked = navigateToProfileScreen
            )
        }

        Scaffold(
            modifier = Modifier
                .scale(scale.value)
                .offset(
                    x = screenPosition.value.dp,
                    y = 0.dp
                ),
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
                            painter = if (drawerState.isOpened()) painterResource(Resources.Icon.Close) else painterResource(Resources.Icon.Menu),
                            contentDescription = "Menu Icon",
                            tint = IconPrimary,
                            modifier = Modifier.clickable {
                                drawerState = drawerState.MakeOpposite()
                            }
                        )
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
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

}