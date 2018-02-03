package com.levnovikov.system_base

import android.util.Log
import com.levnovikov.system_base.back_handling.BackHandler
import com.levnovikov.system_base.node_state.NodeState
import java.util.*

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

abstract class Router {

    private val children = HashMap<Class<out Router>, Router>()

    private var stateDataProvider: StateDataProvider? = null //TODO set to null on node destroy

    private var backHandler: BackHandler? = null

    private fun getChildrenState(): MutableMap<String, NodeState> {
        val stateMap = HashMap<String, NodeState>()
        for (router in children.values) {
            stateMap.putAll(router.getState())
        }
        return stateMap
    }

    fun getState(): Map<String, NodeState> {
        val state = getChildrenState()
        val stateData = stateDataProvider?.run { this.onSaveData() }
        val nodeState = getNodeState(NodeState(this.javaClass.simpleName, stateData)) //TODO refactor to Kotlin class
        state[nodeState.routerClass] = nodeState
        return state
    }

    fun setStateDataProvider(provider: StateDataProvider) {
        stateDataProvider = provider
    }

    fun setBackHandler(handler: BackHandler) {
        backHandler = handler
    }

    protected fun attachNode(nodeHolder: NodeHolder<*>) {
        attachRouter(nodeHolder.build())
    }

    protected fun detachNode(nodeHolder: NodeHolder<out Router>) {
        nodeHolder.router?.let {
            detachRouter(it.javaClass)
            nodeHolder.destroy()
        }
    }

    private fun attachRouter(router: Router) {
        Log.i(">>>>", "attachRouter " + router.javaClass.simpleName + " from " +
                this.javaClass.simpleName)
        if (children.containsKey(router.javaClass)) {
            throw UnsupportedOperationException(String.format("%s already attached", router.javaClass))
        }
        children[router.javaClass] = router
    }

    private fun detachRouter(router: Class<out Router>) {
        Log.i(">>>>", "detachRouter " + router.javaClass.simpleName + " from " +
                this.javaClass.simpleName)
        children.remove(router)
    }

    protected fun detachChildren() {
        Log.i(">>>>", "detachChildren " + this.javaClass.simpleName)
        children.clear()
    }

    abstract fun destroyNode()

    abstract fun getNodeState(nodeState: NodeState): NodeState

    abstract fun setState(state: NodeState)

    fun onBackPressed(): Boolean {
        children.values
                .filter { it.onBackPressed() }
                .forEach { return true }
        backHandler?.let {
            if (it.isLastInStack(this.javaClass)) {
                it.popLastInStack()
                return it.onBackPressed()
            }
        }
        return false
    }

    fun removeFromBackStack() {
        backHandler?.removeFromBackStack(this.javaClass)
    }
}
