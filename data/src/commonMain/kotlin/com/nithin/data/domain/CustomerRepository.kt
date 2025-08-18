package com.nithin.data.domain

import dev.gitlive.firebase.auth.FirebaseUser

interface CustomerRepository {

    fun getUserId() : String?

    suspend fun createCustomer(
        user : FirebaseUser?,
        onSuccess : () -> Unit,
        onError : (String) -> Unit
    )

}