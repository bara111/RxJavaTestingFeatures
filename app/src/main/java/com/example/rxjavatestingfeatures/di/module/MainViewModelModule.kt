package com.example.rxjavatestingfeatures.di.module

import androidx.lifecycle.ViewModel
import com.example.rxjavatestingfeatures.di.scope.ViewModelKey
import com.example.rxjavatestingfeatures.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}