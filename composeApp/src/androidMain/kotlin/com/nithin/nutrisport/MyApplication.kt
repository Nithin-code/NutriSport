package com.nithin.nutrisport

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.nithin.di.initializeKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            application = {
                this@MyApplication
            }
        )
        Firebase.initialize(context = this)
    }

}