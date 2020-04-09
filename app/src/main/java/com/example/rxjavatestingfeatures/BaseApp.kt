package com.example.rxjavatestingfeatures

import android.app.Application
import com.example.rxjavatestingfeatures.di.component.AppComponent
import com.example.rxjavatestingfeatures.di.component.DaggerAppComponent


open class BaseApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}