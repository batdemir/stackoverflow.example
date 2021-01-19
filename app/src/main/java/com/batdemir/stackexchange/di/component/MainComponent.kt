package com.batdemir.stackexchange.di.component

import com.batdemir.stackexchange.ui.main.MainFragment
import com.batdemir.stackexchange.ui.main.detail.DetailFragment
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailFragment)
}