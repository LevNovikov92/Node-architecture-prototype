package com.levnovikov.feature_ride.ride


import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.core_geo.Point
import kotlinx.android.parcel.Parcelize

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class RidePrebookingData(
        val carType: Int,
        val promoCode: String,
        val pickUpPoint: Point?,
        val dropOffPoint: Point?) : Parcelable