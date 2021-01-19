package com.batdemir.stackexchange.di.module

import com.batdemir.stackexchange.BuildConfig
import com.batdemir.stackexchange.data.remote.datasource.MySearchParams
import com.batdemir.stackexchange.data.remote.datasource.UserPagingDataSource
import com.batdemir.stackexchange.data.remote.service.UserPagingService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val BASE_URL = "https://api.stackexchange.com"
    private const val API_VERSION = "/2.2/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG)
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL + API_VERSION)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    //////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun provideServiceUserPaging(retrofit: Retrofit): UserPagingService =
        retrofit.create(UserPagingService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSourceUserPaging(
        service: UserPagingService,
        searchParams: MySearchParams
    ) =
        UserPagingDataSource(service, searchParams)

    //////////////////////////////////////////////////////
}