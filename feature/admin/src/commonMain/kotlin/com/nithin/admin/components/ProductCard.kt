package com.nithin.admin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nithin.admin.data.ProductData
import com.nutrisport.shared.Resources
import nutrisport.feature.admin.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    productData: ProductData = ProductData(
        productImage = Resources.Image.Cat
    )
){

    Row(
        modifier = modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(productData.productImage),
            contentDescription = "Product Image",
            modifier = Modifier
                .size(127.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))



    }

}