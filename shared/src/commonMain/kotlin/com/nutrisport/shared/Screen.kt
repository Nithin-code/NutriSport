package com.nutrisport.shared

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Auth : Screen()

    @Serializable
    data object HomeGraph : Screen()

    @Serializable
    data object ProductOverview : Screen()

    @Serializable
    data object Cart : Screen()

    @Serializable
    data object Catagories : Screen()

    @Serializable
    data object Profile : Screen()

}