package com.example.core_geo

import android.os.Parcelable

import com.google.auto.value.AutoValue

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

@Parcelize
data class Point(val coordinates: Coordinates, val title: String) : Parcelable