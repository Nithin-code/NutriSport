package com.nithin.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.nithin.data.CustomerRepositoryImpl
import com.nithin.data.domain.CustomerRepository
import com.nithin.home.viewmodel.HomeGraphViewModel
import com.nutrisport.auth.AuthViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
}

fun initializeKoin(
    application: ((KoinApplication) -> Unit)? = null
){

    startKoin {
        application?.invoke(this)
        modules(sharedModule)
    }

}