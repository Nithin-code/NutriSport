package com.nutrisport.shared.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nutrisport.shared.Resources
import com.nutrisport.shared.dialog.CountryPickerDialog
import com.nutrisport.shared.domain.Country

@Composable
fun ProfileForm(
    modifier: Modifier,
    firstName : String,
    onFirstNameChanged : (String) -> Unit,
    lastName : String,
    onLastNameChanged : (String) -> Unit,
    email : String,
    city : String?,
    onCityChanged : (String) -> Unit,
    postalCode : String?,
    onPostalCodeChanged : (String?) -> Unit,
    address : String,
    onAddressChanged : (String) -> Unit,
    phoneNumber : String?,
    onPhoneNumberChanged : (String) -> Unit,
    country: Country,
    onCountrySelect : (Country) -> Unit,
){

    var showCountryDialog by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(
        visible = showCountryDialog
    ){
        CountryPickerDialog(
            country = Country.INDIA,
            onDismiss = {showCountryDialog = false},
            onConfirmClick = { selectedCountry ->
                showCountryDialog = false
                onCountrySelect.invoke(selectedCountry)
            }
        )
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .imePadding(), // due to this content of keyboard  our textfield will not be overlapped by keyboard,
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
            value = city ?: "",
            onValueChanged = {onCityChanged.invoke(it)},
            placeholder = "City",
            error = city?.length !in 3..50,
        )

        CustomTextField(
            value = postalCode ?: "",
            onValueChanged = {onPostalCodeChanged.invoke(it)},
            placeholder = "Postal Code",
            error = postalCode==null || postalCode.toString().length !in 3..10,
            keyboardOption = KeyboardOptions().copy(keyboardType = KeyboardType.Number)
        )

        CustomTextField(
            value = address,
            onValueChanged = onAddressChanged,
            placeholder = "Address",
            error = address.length !in 3..50,
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AlertTextField(
                text = "+${country.dailCode}",
                onClick = {
                    showCountryDialog = true
                },
                icon = country.flag,
                modifier = Modifier
            )

            Spacer( modifier = Modifier.width(12.dp))

            CustomTextField(
                value = phoneNumber ?: "",
                onValueChanged = onPhoneNumberChanged,
                placeholder = "Phone no",
                error = phoneNumber?.length !in 3..50,
                keyboardOption = KeyboardOptions().copy(keyboardType = KeyboardType.Number)
            )
        }



    }

}