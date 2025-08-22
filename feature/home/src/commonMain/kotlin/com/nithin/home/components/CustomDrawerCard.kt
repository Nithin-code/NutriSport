package com.nithin.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nithin.home.domain.DrawerItem
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.UbuntuRegularFont
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomDrawerCard(
    item : DrawerItem,
    onClick : ()-> Unit
){

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(99))
            .clickable {
                onClick.invoke()
            }
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 12.dp
            )
    ) {

        Icon(
            painter = painterResource(item.icon),
            contentDescription = "Drawer item icon",
            tint = IconPrimary
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = item.title,
            color = TextPrimary,
            fontFamily = UbuntuRegularFont(),
            fontSize = FontSize.EXTRA_REGULAR
        )

    }

}