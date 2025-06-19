package com.nutrisport.shared

import androidx.compose.runtime.Composable

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import nutrisport.shared.generated.resources.Res
import nutrisport.shared.generated.resources.ubuntu_medium
import nutrisport.shared.generated.resources.ubuntu_regular
import org.jetbrains.compose.resources.Font

@Composable
fun UbuntuRegularFont() = FontFamily(
    Font(Res.font.ubuntu_regular)
)

@Composable
fun UbuntuMediumFont() = FontFamily(
    Font(Res.font.ubuntu_medium)
)

object FontSize{
    val EXTRA_SMALL = 10.sp
    val SMALL = 12.sp

    val REGULAR = 14.sp
    val EXTRA_REGULAR = 16.sp

    val MEDIUM = 18.sp
    val EXTRA_MEDIUM = 20.sp

    val LARGE = 30.sp
    val EXTRA_LARGE = 40.sp
}


