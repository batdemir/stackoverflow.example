package com.batdemir.stackexchange.data.entities.db

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class UserModel(
    @SerializedName("accept_rate")
    val acceptRate: Long?,
    @SerializedName("account_id")
    val accountId: Long,
    val age: Long?,
    @SerializedName("badge_counts")
    val badgeCount: BadgeModel,
    @SerializedName("creation_date")
    val creationDate: Long,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("is_employee")
    val isEmployee: Boolean,
    @SerializedName("last_access_date")
    val lastAccessDate: Long,
    @SerializedName("last_modified_date")
    val lastModifiedDate: Long?,
    val link: String?,
    val location: String?,
    @SerializedName("profile_image")
    val profileImage: String?,
    val reputation: Long,
    @SerializedName("reputation_change_day")
    val reputationChangeDay: Long,
    @SerializedName("reputation_change_month")
    val reputationChangeMonth: Long,
    @SerializedName("reputation_change_quarter")
    val reputationChangeQuarter: Long,
    @SerializedName("reputation_change_week")
    val reputationChangeWeek: Long,
    @SerializedName("reputation_change_year")
    val reputationChangeYear: Long,
    @SerializedName("timed_penalty_date")
    val timedPenaltyDate: Long?,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("website_url")
    val websiteUrl: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readLong(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readParcelable(BadgeModel::class.java.classLoader)!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(acceptRate)
        parcel.writeLong(accountId)
        parcel.writeValue(age)
        parcel.writeParcelable(badgeCount, flags)
        parcel.writeString(displayName)
        parcel.writeByte(if (isEmployee) 1 else 0)
        parcel.writeString(link)
        parcel.writeString(location)
        parcel.writeString(profileImage)
        parcel.writeLong(reputation)
        parcel.writeLong(reputationChangeDay)
        parcel.writeLong(reputationChangeMonth)
        parcel.writeLong(reputationChangeQuarter)
        parcel.writeLong(reputationChangeWeek)
        parcel.writeLong(reputationChangeYear)
        parcel.writeLong(userId)
        parcel.writeString(userType)
        parcel.writeString(websiteUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}