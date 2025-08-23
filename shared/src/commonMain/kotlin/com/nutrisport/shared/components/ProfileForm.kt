package com.nutrisport.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileForm(
    modifier: Modifier,
    firstName : String,
    onFirstNameChanged : (String) -> Unit,
    lastName : String,
    onLastNameChanged : (String) -> Unit,
    email : String,
    city : String,
    onCityChanged : (String) -> Unit,
    postalCode : Int?,
    onPostalCodeChanged : (Int?) -> Unit,
    address : String,
    onAddressChanged : (String) -> Unit,
    phoneNumber : String?,
    onPhoneNumberChanged : (String) -> Unit
){

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .verticalScroll(state = rememberScrollState())
            .imePadding(), // due to this content of our textfield will not be overlapped by keyboard,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        CustomTextField(
            value = firstName,
            onValueChanged = onFirstNameChanged,
            placeholder = "First Name",
            error = firstName.length !in 3..50
        )

        CustomTextField(
            value = lastName,
            onValueChanged = onLastNameChanged,
            placeholder = "Last Name",
            error = lastName.length !in 3..50
        )

        CustomTextField(
            value = email,
            onValueChanged = {},
            placeholder = "Email",
            error = email.length !in 3..50,
            enabled = false
        )

        CustomTextField(
            value = city,
            onValueChanged = {onCityChanged.invoke(it)},
            placeholder = "City",
            error = city.length !in 3..50,
        )

        CustomTextField(
            value = "${postalCode ?: ""}",
            onValueChanged = {onPostalCodeChanged.invoke(it.toIntOrNull())},
            placeholder = "Postal Code",
            error = postalCode.toString().length !in 3..10,
        )

        CustomTextField(
            value = address,
            onValueChanged = onAddressChanged,
            placeholder = "Address",
            error = address.length !in 3..50,
        )

        CustomTextField(
            value = phoneNumber ?: "",
            onValueChanged = onPhoneNumberChanged,
            placeholder = "Phone no",
            error = phoneNumber?.length !in 3..50,
        )

    }

}