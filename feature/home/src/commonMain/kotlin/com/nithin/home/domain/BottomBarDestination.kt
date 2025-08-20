package com.nithin.home.domain

import com.nutrisport.shared.Resources
import com.nutrisport.shared.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Resource

enum class BottomBarDestination(
    val icon: DrawableResource,
    val title: String,
    val screen: Screen
) {

    PRODUCT_OVERVIEW(
        icon = Resources.Icon.Home,
        title = "Nutri Sport",
        screen = Screen.ProductOverview
    ),

    Cart(
        icon = Resources.Icon.ShoppingCart,
        title = "Cart",
        screen = Screen.Cart
    ),

    Categories(
        icon = Resources.Icon.Categories,
        title = "Categories",
        screen = Screen.Catagories
    )

}