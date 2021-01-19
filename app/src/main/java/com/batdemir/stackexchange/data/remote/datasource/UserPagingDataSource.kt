package com.batdemir.stackexchange.data.remote.datasource

import androidx.paging.PagingSource
import com.batdemir.stackexchange.data.entities.db.UserModel
import com.batdemir.stackexchange.data.remote.service.UserPagingService

class UserPagingDataSource(
    private val service: UserPagingService,
    private val searchParams: MySearchParams
) : PagingSource<MyLoadParams, UserModel>() {
    override suspend fun load(params: LoadParams<MyLoadParams>): LoadResult<MyLoadParams, UserModel> {
        return try {
            val key = params.key ?: MyLoadParams(START_PAGE_INDEX)
            val response = service.get(
                key.page,
                searchParams.order.name,
                searchParams.sort.name,
                searchParams.query,
                searchParams.site
            )
            val prevKey =
                when (key.page) {
                    START_PAGE_INDEX -> null
                    else -> MyLoadParams(key.page - 1)
                }
            val nextKey =
                when {
                    !response.hasMore -> null
                    response.items.isNullOrEmpty() -> null
                    else -> MyLoadParams(key.page + 1)
                }
            LoadResult.Page(
                data = response.items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

data class MyLoadParams(
    var page: Long
)

data class MySearchParams(
    val query: String,
    val order: Order,
    val sort: Sort,
    val site: String
)

enum class Order(name: String) {
    DESC("desc"),
    ASC("acs")
}

enum class Sort(name: String) {
    REPUTATION("reputation"),
    CREATION("creation"),
    NAME("name"),
    MODIFIED("modified")
}

private const val START_PAGE_INDEX: Long = 1
private const val MAX_PAGE_INDEX: Long = 999