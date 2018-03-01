package com.levnovikov.system_base.node_state

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by lev.novikov
 * Date: 27/12/17.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class NodeState(
        val data: Parcelable?,
        private val activeNodes: Set<String> = emptySet()) : Parcelable {

    fun contains(_class: Class<*>): Boolean {
        return activeNodes.contains(_class.simpleName) //TODO change to canonical after testing
    }
}
