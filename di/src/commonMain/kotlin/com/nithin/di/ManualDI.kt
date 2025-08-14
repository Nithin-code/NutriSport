package com.nithin.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin


fun initializeKoin(
    application: ((KoinApplication) -> Unit)? = null
){


    startKoin {
        application?.invoke(this)
    }

}