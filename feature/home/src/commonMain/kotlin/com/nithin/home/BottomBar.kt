package com.nithin.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nithin.home.domain.BottomBarDestination
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.IconSecondary
import com.nutrisport.shared.SurfaceLighter
import org.jetbrains.compose.resources.painterResource


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selected : BottomBarDestination,
    onSelected : (BottomBarDestination) -> Unit
){

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 12.dp),
        color = SurfaceLighter,
        shape = RoundedCornerShape(12.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(
                    horizontal = 36.dp,
                    vertical = 24.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {



            BottomBarDestination.entries.forEach { item->
                val iconColor = animateColorAsState(
                    if (selected == item) IconSecondary else IconPrimary
                )
                Icon(
                    modifier = Modifier.clickable {
                        onSelected(item)
                    },
                    painter = painterResource(item.icon),
                    contentDescription = item.title,
                    tint = iconColor.value
                )

            }

        }

    }

}


