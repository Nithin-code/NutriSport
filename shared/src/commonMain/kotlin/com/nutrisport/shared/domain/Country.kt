package com.nutrisport.shared.domain

import com.nutrisport.shared.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class Country(
    val dailCode : Int,
    val code : String,
    val flag : DrawableResource
) {

    SERBIA(
        dailCode = 301,
        code = "RS",
        flag = Resources.Flag.Serbia
    ),

    INDIA(
        dailCode = 91,
        code = "IND",
        flag = Resources.Flag.India
    ),

    USA(
        dailCode = 1,
        code = "USA",
        flag = Resources.Flag.Usa
    ),


}