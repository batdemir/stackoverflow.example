package com.batdemir.stackexchange.di.module

import com.batdemir.stackexchange.data.storage.SharedPreferencesStorage
import com.batdemir.stackexchange.data.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}