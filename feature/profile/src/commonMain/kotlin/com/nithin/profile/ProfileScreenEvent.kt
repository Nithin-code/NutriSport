package com.nithin.profile

sealed interface ProfileScreenEvent{

    data class UpdateFirstName(val value : String) : ProfileScreenEvent

    data class UpdateLastName(val value : String) : ProfileScreenEvent

    data class UpdateCity(val value : String): ProfileScreenEvent

    data class UpdatePostalCode(val value : String): ProfileScreenEvent

    data class UpdateAddress(val value : String): ProfileScreenEvent

    data class UpdateMobileNo(val value : String): ProfileScreenEvent

    data class UpdateBtnClick(
        val onSuccess : () -> Unit,
        val onError : (String) -> Unit
    ) : ProfileScreenEvent

}