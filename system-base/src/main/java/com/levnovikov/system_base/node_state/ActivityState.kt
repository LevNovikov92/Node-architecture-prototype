package com.levnovikov.system_base.node_state

import android.annotation.SuppressLint
import android.os.Parcelable
import com.levnovikov.system_base.Router
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by lev.novikov
 * Date: 29/12/17.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ActivityState(
        private val backStack: Stack<String> = Stack(),
        private val stateMap: Map<String, NodeState> = mapOf()) : Parcelable {


    fun findNodeState(routerClass: Class<out Router>): NodeState? = stateMap[routerClass.simpleName]

    fun addToBackStack(_class: Class<out Router>) = backStack.add(_class.simpleName) //TODO replace to canonical name after testing

    fun isLastInBackStack(_class: Class<out Router>): Boolean =
            !backStack.isEmpty() && backStack.peek() == _class.simpleName //TODO replace to canonical name after testing

    fun popLastInBackStack() {
        backStack.pop()
    }

    fun removeFromBackStack(_class: Class<out Router>) {
        backStack.remove(_class.simpleName) //TODO replace to canonical name after testing
    }
}
