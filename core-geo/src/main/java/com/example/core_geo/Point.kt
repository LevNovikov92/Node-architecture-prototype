package com.example.core_geo

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class Point(val coordinates: Coordinates, val title: String) : Parcelable