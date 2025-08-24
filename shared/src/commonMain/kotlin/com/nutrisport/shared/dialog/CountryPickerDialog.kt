package com.nutrisport.shared.dialog


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nutrisport.shared.Alpha
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconWhite
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Surface
import com.nutrisport.shared.SurfaceLighter
import com.nutrisport.shared.SurfaceSecondary
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.TextSecondary
import com.nutrisport.shared.UbuntuRegularFont
import com.nutrisport.shared.components.CustomTextField
import com.nutrisport.shared.domain.Country
import org.jetbrains.compose.resources.painterResource



@Composable
fun CountryPickerDialog(
    country: Country,
    onDismiss : () -> Unit,
    onConfirmClick : (Country) -> Unit
){
    var selectedCountry by remember(country) {
        mutableStateOf(country)
    }

    val allCountries = remember {
        Country.entries.toList()
    }

    val filteredCountries = remember {
        mutableStateListOf<Country>().apply {
            addAll(allCountries)
        }
    }

    var searchQuery by remember {
        mutableStateOf("")
    }

    AlertDialog(
        containerColor = Surface,
        title = {
            Text(
                text = "Pick a Country",
                fontFamily = UbuntuRegularFont(),
                fontSize = FontSize.EXTRA_MEDIUM,
                color = TextPrimary
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                CustomTextField(
                    value = searchQuery,
                    placeholder = "Dail Code",
                    onValueChanged = {query->
                        searchQuery = query
                        if (searchQuery.isNotBlank()) {
                            val filtered = allCountries.filterByCountry(query)
                            filteredCountries.clear()
                            filteredCountries.addAll(filtered)
                        }else{
                            filteredCountries.clear()
                            filteredCountries.addAll(allCountries)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    items(
                        items = filteredCountries,
                        key = {it.ordinal}
                    ){ country->
                        CountryPicker(
                            country = country,
                            isSelected = (selectedCountry == country),
                            onSelect = {selectedCountry = country}
                        )
                    }

                }
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClick.invoke(selectedCountry)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = TextSecondary
                )
            ){
                Text(
                    text = "Confirm",
                    fontSize = FontSize.REGULAR,
                    fontWeight = FontWeight.Medium,
                    fontFamily = UbuntuRegularFont()
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = TextPrimary.copy(alpha = Alpha.HALF)
                )
            ){
                Text(
                    text = "Cancel",
                    fontSize = FontSize.REGULAR,
                    fontWeight = FontWeight.Medium,
                    fontFamily = UbuntuRegularFont()
                )
            }
        }
    )

}


@Composable
private fun CountryPicker(
    modifier: Modifier = Modifier,
    country: Country,
    isSelected: Boolean,
    onSelect : () -> Unit
){
    val saturated = remember {
        Animatable( if (isSelected) 1f else 0f)
    }

    LaunchedEffect(isSelected){
        saturated.animateTo(if (isSelected) 1f else 0f)
    }

    val colorMatrix = remember(saturated.value) {
        ColorMatrix().apply {
            setToSaturation(saturated.value)
        }
    }

    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable{
                onSelect.invoke()
            }
    ) {

        Image(
            modifier = Modifier.size(14.dp),
            painter = painterResource(country.flag),
            contentDescription = "flag",
            colorFilter = ColorFilter.colorMatrix(colorMatrix)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = "+${country.dailCode} (${country.code})",
            fontFamily = UbuntuRegularFont(),
            fontSize = FontSize.REGULAR,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.width(12.dp))

        Selector(
            isSelected = isSelected
        )

    }

}


@Composable
private fun Selector(
    modifier: Modifier = Modifier,
    isSelected : Boolean = false
){

    val animateColor = animateColorAsState(
        if (isSelected) SurfaceSecondary else SurfaceLighter
    )

    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = animateColor.value
    ) {
        Box(
            modifier = Modifier
                .size(20.dp),
            contentAlignment = Alignment.Center
        ){
            AnimatedVisibility(
                visible = isSelected
            ){
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(Resources.Icon.Checkmark),
                    contentDescription = "check mark",
                    tint = IconWhite
                )
            }
        }
    }

}


fun List<Country>.filterByCountry(query : String) : List<Country>{
    val queryInt = query.lowercase().toIntOrNull()
    return this.filter { it->
        it.name.lowercase().contains(query.lowercase()) || (queryInt!=null && it.dailCode == queryInt)
    }
}












































































































