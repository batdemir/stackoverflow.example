package com.batdemir.stackexchange.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batdemir.stackexchange.data.entities.db.UserModel
import com.batdemir.stackexchange.data.remote.datasource.MySearchParams
import com.batdemir.stackexchange.data.remote.datasource.UserPagingDataSource
import com.batdemir.stackexchange.data.remote.service.UserPagingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserPagingService
) {
    fun getPagingDataSource(searchParams: MySearchParams): Flow<PagingData<UserModel>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingDataSource(service, searchParams) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }
}