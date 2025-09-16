package com.nithin.admin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nithin.admin.data.ProductData
import com.nutrisport.shared.BorderIdle
import com.nutrisport.shared.BorderSecondary
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.Resources
import com.nutrisport.shared.SurfaceLighter
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.TextSecondary
import com.nutrisport.shared.UbuntuMediumFont
import com.nutrisport.shared.UbuntuRegularFont
import nutrisport.feature.admin.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    productData: ProductData = ProductData(
        productImage = Resources.Image.Cat
    )
){

    Surface(
        color = SurfaceLighter,
        border = BorderStroke(
            width = 1.dp,
            color = BorderIdle
        ),
        shape = RoundedCornerShape(12.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(productData.productImage),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(127.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {

                Text(
                    text = productData.productTitle,
                    fontSize = FontSize.MEDIUM,
                    color = TextPrimary,
                    fontFamily = UbuntuMediumFont()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = productData.subTitle,
                    fontSize = FontSize.REGULAR,
                    color = TextPrimary,
                    maxLines = 3,
                    fontFamily = UbuntuRegularFont()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = productData.productPrice,
                    color = TextSecondary,
                    fontSize = FontSize.MEDIUM,
                    textAlign = TextAlign.End,
                    fontFamily = UbuntuRegularFont()
                )

            }

        }

    }

}