package com.batdemir.stackexchange.di.component

import android.content.Context
import com.batdemir.stackexchange.di.module.NetworkModule
import com.batdemir.stackexchange.di.module.RepositoryModule
import com.batdemir.stackexchange.di.module.StorageModule
import com.batdemir.stackexchange.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        StorageModule::class
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(mainActivity: MainActivity)
    fun mainComponent(): MainComponent.Factory
}