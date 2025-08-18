package com.nithin.data

import com.nithin.data.domain.CustomerRepository
import com.nutrisport.shared.domain.Customer
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.firestore.firestore

class CustomerRepositoryImpl : CustomerRepository {

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if (user != null){
                val customerCollection = Firebase.firestore.collection(collectionPath = "customer")
                val customer = Customer(
                    id = user.uid.toString(),
                    firstName = user.displayName?.split(" ")?.firstOrNull() ?: "Unknown",
                    lastName = user.displayName?.split(" ")?.lastOrNull() ?: "Unknown",
                    email = user.email ?: "Unknown"
                )
                val customerExist = customerCollection.document(user.uid).get().exists
                if (customerExist){
                    onSuccess()
                }else{
                    customerCollection.document(
                        user.uid
                    ).set(
                        customer
                    )
                    onSuccess()
                }
            }else{
                onError("Invalid User")
            }
        }catch (t : Exception){
            onError("Unable to create user because : ${t.message}")
        }
    }


}