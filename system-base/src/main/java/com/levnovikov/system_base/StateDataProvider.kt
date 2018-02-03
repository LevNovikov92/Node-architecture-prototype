package com.levnovikov.system_base

import android.os.Parcelable

/**
 * Created by lev.novikov
 * Date: 29/12/17.
 */

interface StateDataProvider {
    fun stateData(): Parcelable?
}
