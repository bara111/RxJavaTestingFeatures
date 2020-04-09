package com.example.rxjavatestingfeatures.ui.main.di.component

import com.example.rxjavatestingfeatures.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}