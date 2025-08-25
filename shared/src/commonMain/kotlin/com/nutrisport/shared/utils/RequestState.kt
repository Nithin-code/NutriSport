package com.nutrisport.shared.utils

import androidx.compose.runtime.Composable

sealed class RequestState<out T> {

    data object Idle : RequestState<Nothing>()

    data object Loading : RequestState<Nothing>()

    data class Success<out T>(val data: T) : RequestState<T>()

    data class Error(val message: String) : RequestState<Nothing>()

    fun isLoading(): Boolean = this is Loading

    fun isIdle(): Boolean = this is Idle

    fun isSuccess(): Boolean = this is Success

    fun isError() : Boolean = this is Error

    fun getSuccessData() = (this as Success).data

    fun getSuccessDataOrNull() = if (this.isSuccess()) this.getSuccessData() else null

    fun getErrorMessage() = (this as Error).message


}