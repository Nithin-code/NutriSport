package com.nithin.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nithin.home.domain.DrawerItem
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.TextSecondary
import com.nutrisport.shared.UbuntuMediumFont
import com.nutrisport.shared.UbuntuRegularFont

@Composable
fun CustomDrawer(
    padding: PaddingValues,
    onSignOutClicked : () -> Unit
) {
//    var customDrawerPosition = animateFloatAsState(
//        targetValue = if (customDrawerPosition) 0f else -600f,
//        animationSpec = tween(durationMillis = 500)
//    )
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(0.65f)
            .fillMaxHeight()
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "NUTRISPORT",
            color = TextSecondary,
            fontFamily = UbuntuRegularFont(),
            fontSize = FontSize.LARGE,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Healthy Lifestyle",
            color = TextPrimary,
            fontFamily = UbuntuRegularFont(),
            fontSize = FontSize.REGULAR,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        DrawerItem.entries.take(5).forEach { item->
            CustomDrawerCard(
                item = item,
                onClick = {
                    when(item){
                        DrawerItem.PROFILE -> {

                        }
                        DrawerItem.BLOG -> {

                        }
                        DrawerItem.LOCATIONS -> {

                        }
                        DrawerItem.Contact -> {

                        }
                        DrawerItem.SIGNOUT -> {
                            onSignOutClicked.invoke()
                        }
                        DrawerItem.ADMIN -> {

                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        CustomDrawerCard(
            item = DrawerItem.ADMIN,
            onClick = {

            }
        )

    }
}