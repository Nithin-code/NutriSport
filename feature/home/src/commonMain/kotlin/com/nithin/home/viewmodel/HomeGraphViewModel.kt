package com.nithin.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithin.data.domain.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeGraphViewModel (
    val customerRepository: CustomerRepository
) : ViewModel(){

    fun signOutUser(
        onSuccess : () -> Unit,
        onError : (String) -> Unit
    ){

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { customerRepository.signOutUser() }
            if (response.isSuccess()){
                onSuccess()
            }else{
                onError.invoke(response.getErrorMessage())
            }
        }

    }

}