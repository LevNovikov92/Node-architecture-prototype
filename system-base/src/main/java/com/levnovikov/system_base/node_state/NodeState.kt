package com.levnovikov.system_base.node_state

import android.annotation.SuppressLint
import android.os.Parcelable
import com.levnovikov.system_base.NodeHolder
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by lev.novikov
 * Date: 27/12/17.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class NodeState(
        val routerClass: String,
        val data: Parcelable?,
        private val activeNodes: HashSet<String> = HashSet()) : Parcelable {

    fun contains(_class: Class<*>): Boolean {
        return activeNodes.contains(_class.simpleName) //TODO change to canonical after testing
    }

    fun <T : NodeHolder<*>> addNodeBuilder(_class: Class<T>) {
        activeNodes.add(_class.simpleName) //TODO change to canonical after testing
    }
}
