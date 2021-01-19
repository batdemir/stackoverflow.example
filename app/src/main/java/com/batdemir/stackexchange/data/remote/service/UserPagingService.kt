package com.batdemir.stackexchange.data.remote.service

import com.batdemir.stackexchange.data.entities.response.ApiResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UserPagingService {
    @GET("users")
    suspend fun get(
        @Query("page") page: Long,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("inname") query: String,
        @Query("site") site: String
    ): ApiResponseModel
}