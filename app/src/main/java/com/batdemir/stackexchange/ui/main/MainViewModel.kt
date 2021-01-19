package com.batdemir.stackexchange.ui.main

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.batdemir.stackexchange.data.entities.db.UserModel
import com.batdemir.stackexchange.data.remote.datasource.MySearchParams
import com.batdemir.stackexchange.data.remote.datasource.Order
import com.batdemir.stackexchange.data.remote.datasource.Sort
import com.batdemir.stackexchange.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    companion object {
        private const val SITE_NAME = "stackoverflow"
    }

    fun search(query: String): Flow<PagingData<UserModel>> = repository.getPagingDataSource(
        MySearchParams(
            query,
            Order.ASC,
            Sort.NAME,
            SITE_NAME
        )
    )
}