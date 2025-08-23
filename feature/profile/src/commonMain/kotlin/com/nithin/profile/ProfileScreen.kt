package com.nithin.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Surface
import com.nutrisport.shared.components.CustomButton
import com.nutrisport.shared.components.ProfileForm

@Composable
fun ProfileScreen(

){

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
        )
    }

}