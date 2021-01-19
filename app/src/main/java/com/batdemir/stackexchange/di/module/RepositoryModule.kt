package com.batdemir.stackexchange.di.module

import com.batdemir.stackexchange.data.remote.service.UserPagingService
import com.batdemir.stackexchange.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepositoryUser(service: UserPagingService) =
        UserRepository(service)
}