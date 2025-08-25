package com.nithin.data

import com.nithin.data.domain.CustomerRepository
import com.nutrisport.shared.domain.Customer
import com.nutrisport.shared.utils.RequestState
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class CustomerRepositoryImpl : CustomerRepository {

    override fun getUserId() : String? {
        return Firebase.auth.currentUser?.uid
    }

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

    override fun readCustomerFlow(): Flow<RequestState<Customer>> = flow {
        try {
            val userID = getUserId()
            if (userID!=null){
                val database = Firebase.firestore
                database.collection(collectionPath = "customer")
                    .document(userID)
                    .snapshots
                    .collectLatest { document->
                        if (document.exists){
                            val customer = Customer(
                                id = document.id,
                                firstName = document.get(field = "firstName"),
                                lastName = document.get(field = "lastName"),
                                email = document.get(field = "email"),
                                postalCode = document.get(field = "postalCode"),
                                address = document.get(field = "address"),
                                city = document.get(field = "city"),
                                mobileNo = document.get(field = "mobileNo"),
                            )
                            emit(RequestState.Success(data = customer))
                        }else{
                            emit(RequestState.Error("Queried customer document does not exists."))
                        }
                    }
            }else{
                emit(RequestState.Error("User not found.."))
            }
        }catch (t : Exception){
            emit(RequestState.Error("Error while reading customer info : ${t.message}"))
        }
    }

    override suspend fun signOutUser(): RequestState<Unit> {
        return try {
            Firebase.auth.signOut()
            RequestState.Success(data = Unit)
        }catch (t : Exception){
            RequestState.Error("error while signOut : ${t.message}")
        }
    }


}