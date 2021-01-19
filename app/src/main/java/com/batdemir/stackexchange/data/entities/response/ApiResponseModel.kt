package com.batdemir.stackexchange.data.entities.response

import com.batdemir.stackexchange.data.entities.db.UserModel
import com.google.gson.annotations.SerializedName

data class ApiResponseModel(
    val items: List<UserModel>,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("quota_max")
    val quotaMax: Long,
    @SerializedName("quota_remaining")
    val quotaRemaining: Long,
)