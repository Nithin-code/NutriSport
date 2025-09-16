package com.nithin.admin.data

import org.jetbrains.compose.resources.DrawableResource

data class ProductData(
    val productImage : DrawableResource,
    val productTitle : String = "NUTREND 100% WHEY PROTEIN",
    val subTitle : String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim  veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea  commodo consequat. Duis aute irure dolor in reprehenderit in voluptate  velit esse cillum dolore eu fugiat nulla pariatur.",
    val productPrice : String = "$ 56.00",
)
