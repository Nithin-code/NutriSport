package com.nutrisport.shared.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nutrisport.shared.ButtonDisabled
import com.nutrisport.shared.ButtonPrimary
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.Resources
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.UbuntuRegularFont
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text : String,
    height : Dp = 56.dp,
    onClick : () -> Unit,
    icon : DrawableResource? = null,
    enabled : Boolean = true
){

    val color = animateColorAsState(
        targetValue = if (enabled) ButtonPrimary else ButtonDisabled
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        color = color.value,
        enabled = enabled
    ){

        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            icon?.let {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(it),
                    contentDescription = "icon",
                    tint = IconPrimary
                )
                Spacer(
                    modifier = Modifier.width(12.dp)
                )
            }
            Text(
                text = text,
                color = TextPrimary,
                fontFamily = UbuntuRegularFont(),
                fontSize = FontSize.REGULAR
            )
        }

    }

}

@Preview()
@Composable
fun Prev(){
    CustomButton(
        text = "Update",
        icon = Resources.Icon.Checkmark,
        onClick = {

        }
    )
}