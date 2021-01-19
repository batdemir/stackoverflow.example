package com.batdemir.stackexchange.data.entities.db

import android.os.Parcel
import android.os.Parcelable

data class BadgeModel(
    val bronze: Long,
    val silver: Long,
    val gold: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(bronze)
        parcel.writeLong(silver)
        parcel.writeLong(gold)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BadgeModel> {
        override fun createFromParcel(parcel: Parcel): BadgeModel {
            return BadgeModel(parcel)
        }

        override fun newArray(size: Int): Array<BadgeModel?> {
            return arrayOfNulls(size)
        }
    }
}