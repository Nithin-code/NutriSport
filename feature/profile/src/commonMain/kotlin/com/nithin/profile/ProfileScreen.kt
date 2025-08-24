package com.nithin.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Surface
import com.nutrisport.shared.components.CustomButton
import com.nutrisport.shared.components.ProfileForm
import com.nutrisport.shared.domain.Country

@Composable
fun ProfileScreen(

){

    var country by remember {
        mutableStateOf(Country.INDIA)
    }

    Box(
        modifier = Modifier
            .background(Surface)
            .systemBarsPadding()
    ) {
        ProfileForm(
            modifier = Modifier,
            firstName = "Nithin",
            onFirstNameChanged = {},
            lastName = "Kondeti",
            onLastNameChanged = {},
            email = "",
            city = "",
            onCityChanged = {},
            postalCode = null,
            onPostalCodeChanged = {},
            address = "",
            onAddressChanged = {},
            phoneNumber = "",
            onPhoneNumberChanged = {},
            country = country,
            onCountrySelect = { it->
                country = it
            }
        )
    }

}