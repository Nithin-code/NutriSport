package com.nutrisport.shared.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nutrisport.shared.Alpha
import com.nutrisport.shared.BorderError
import com.nutrisport.shared.BorderIdle
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.SurfaceLighter
import com.nutrisport.shared.TextPrimary

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value : String,
    placeholder : String? = null,
    onValueChanged : (String) -> Unit,
    error : Boolean = false,
    enabled : Boolean = true,
    expanded : Boolean = false,
    keyboardOption: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text
    )
){

    val borderColor = animateColorAsState(
        targetValue = if (error) BorderError else BorderIdle
    )

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = borderColor.value
            ),
        value = value,
        onValueChange = onValueChanged,
        enabled = enabled,
        placeholder = if (placeholder !=null) {
            {
                Text(
                    modifier = Modifier.alpha(Alpha.DISABLED),
                    text = placeholder,
                    fontSize = FontSize.REGULAR
                )
            }
        } else null,
        singleLine = !expanded,
        keyboardOptions = keyboardOption,
        shape = RoundedCornerShape(6.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SurfaceLighter,
            disabledContainerColor = SurfaceLighter,
            disabledTextColor = TextPrimary.copy(alpha = Alpha.DISABLED),
            unfocusedTextColor = TextPrimary,
            focusedTextColor = TextPrimary
        )
    )

}
