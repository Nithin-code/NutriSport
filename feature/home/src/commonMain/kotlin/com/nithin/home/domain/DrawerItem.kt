package com.nithin.home.domain

import com.nutrisport.shared.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class DrawerItem(
    val title : String,
    val icon : DrawableResource
) {

    PROFILE(
        title = "Profile",
        icon = Resources.Icon.Person
    ),
    BLOG(
        title = "Blog",
        icon = Resources.Icon.Book
    ),

    LOCATIONS(
        title = "Locations",
        icon = Resources.Icon.MapPin
    ),

    Contact(
        title = "Contact",
        icon = Resources.Icon.Edit
    ),

    SIGNOUT(
        title = "Sign Out",
        icon = Resources.Icon.SignOut
    ),
    ADMIN(
        title = "Admin Panel",
        icon = Resources.Icon.Unlock
    ),

}