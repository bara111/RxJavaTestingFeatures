package com.example.rxjavatestingfeatures.di.component

import android.content.Context
import com.example.rxjavatestingfeatures.di.AppSubComponents
import com.example.rxjavatestingfeatures.di.module.MainViewModelModule
import com.example.rxjavatestingfeatures.di.module.RetrofitModule
import com.example.rxjavatestingfeatures.di.module.ViewModelFactoryModule
import com.example.rxjavatestingfeatures.ui.main.di.component.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, AppSubComponents::class, ViewModelFactoryModule::class, MainViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
}