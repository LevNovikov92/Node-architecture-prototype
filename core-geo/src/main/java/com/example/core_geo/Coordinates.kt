package com.example.core_geo

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class Coordinates(val x: Double, val y: Double) : Parcelable
