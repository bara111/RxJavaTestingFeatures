package com.example.rxjavatestingfeatures.ui.di.component

import com.example.rxjavatestingfeatures.ui.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}