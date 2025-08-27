package com.nithin.data.domain

import com.nutrisport.shared.domain.Customer
import com.nutrisport.shared.utils.RequestState
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    fun getUserId() : String?

    suspend fun createCustomer(
        user : FirebaseUser?,
        onSuccess : () -> Unit,
        onError : (String) -> Unit
    )

    fun readCustomerFlow() : Flow<RequestState<Customer>>

    suspend fun signOutUser() : RequestState<Unit>

    suspend fun updateCustomer(
        customer: Customer, onSuccess: () -> Unit, onError: (String) -> Unit
    )

}