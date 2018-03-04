package com.levnovikov.system_base

import android.support.annotation.UiThread
import android.support.annotation.VisibleForTesting
import com.levnovikov.system_base.back_handling.BackHandler
import com.levnovikov.system_base.exceptions.RouterAlreadyAttachedException
import com.levnovikov.system_base.exceptions.ViewIsAlreadyAttachedException
import com.levnovikov.system_base.node_state.NodeState
import timber.log.Timber
import java.util.*

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

abstract class Router {

    private val children = HashMap<Class<out Router>, Router>()

    private fun getChildrenState(): MutableMap<String, NodeState> {
        val stateMap = HashMap<String, NodeState>()
        children.values.forEach { stateMap.putAll(it.getState()) }
        return stateMap
    }

    private var stateDataProvider: StateDataProvider? = null //TODO set to null on node destroy

    internal fun setStateDataProvider(provider: StateDataProvider) {
        stateDataProvider = provider
    }

    fun getState(): Map<String, NodeState> {
        val state = getChildrenState()
        val stateData = stateDataProvider?.run { this.onSaveData() }
        state[this.javaClass.simpleName] = NodeState(stateData, nodes())
        return state
    }

    private var backHandler: BackHandler? = null

    internal fun setBackHandler(handler: BackHandler) {
        backHandler = handler
    }

    @UiThread
    protected fun attachNode(nodeHolder: NodeHolder<*>) {
        try {
            attachRouter(nodeHolder.build())
        } catch (e: ViewIsAlreadyAttachedException) {
            Timber.i(">>>> ${e.message}")
        }
    }

    @UiThread
    protected fun detachNode(nodeHolder: NodeHolder<out Router>) {
        nodeHolder.router?.let {
            detachRouter(it.javaClass)
            nodeHolder.destroy()
        }
    }

    private fun attachRouter(router: Router) {
        Timber.i(">>>>> attachRouter " + router.javaClass.simpleName + " from " +
                this.javaClass.simpleName)
        if (children.containsKey(router.javaClass)) {
            throw RouterAlreadyAttachedException(router)
        }
        children[router.javaClass] = router
    }

    private fun detachRouter(router: Class<out Router>) {
        Timber.i(">>>>  detachRouter " + router.javaClass.simpleName + " from " +
                this.javaClass.simpleName)
        children.remove(router)
    }

    internal fun detachAllChildren() {
        holders.forEach(::detachNode)
    }

    abstract val holders: Set<NodeHolder<*>>

    internal fun nodes(): Set<String> = holders
            .filter(NodeHolder<*>::isActive)
            .map { it::class.java.simpleName }
            .toSet()

    internal fun setState(state: NodeState) = holders
            .filter { state.contains(it.javaClass) }
            .forEach(::attachNode)

    fun onBackPressed(): Boolean {
        for (router in children.values) {
            if (router.onBackPressed()) {
                return true
            }
        }
        backHandler?.let {
            if (it.isLastInStack(this.javaClass)) {
                it.popLastInStack()
                return it.onBackPressed()
            }
        }
        return false
    }

    internal fun removeFromBackStack() {
        backHandler?.removeFromBackStack(this.javaClass)
    }

    @VisibleForTesting
    internal fun testChildren(): HashMap<Class<out Router>, Router> {
        return children
    }
}
