package com.example.rxjavatestingfeatures.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.rxjavatestingfeatures.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}