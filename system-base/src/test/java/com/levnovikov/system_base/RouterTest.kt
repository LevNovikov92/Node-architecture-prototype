package com.levnovikov.system_base

import com.levnovikov.system_base.back_handling.BackHandler
import com.levnovikov.system_base.exceptions.RouterAlreadyAttachedException
import com.levnovikov.system_base.node_state.ActivityState
import com.levnovikov.system_base.node_state.NodeState
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert
import junit.framework.AssertionFailedError
import org.junit.Before
import org.junit.Test
import org.mockito.internal.verification.Times
import java.util.*

/**
 * Author: lev.novikov
 * Date: 3/3/18.
 */
class RouterTest {

    abstract class TestRouter : Router() {
        fun attachTestNode(nodeHolder: NodeHolder<*>) {
            attachNode(nodeHolder)
        }

        fun detachTestNode(nodeHolder: NodeHolder<*>) {
            detachNode(nodeHolder)
        }
    }

    class ChildRouter1 : TestRouter() {
        override val holders: Set<NodeHolder<*>>
            get() = emptySet()
    }

    class ChildRouter2 : TestRouter() {
        override val holders: Set<NodeHolder<*>>
            get() = emptySet()
    }

    private lateinit var child1: ChildRouter1
    private lateinit var child2: ChildRouter2
    private lateinit var nodeHolder1: NodeHolder<*>
    private lateinit var nodeHolder2: NodeHolder<*>
    private lateinit var router: TestRouter

    @Before
    fun setUp() {
        child1 = ChildRouter1()
        child2 = ChildRouter2()

        nodeHolder1 = getFakeNodeHolder(child1)
        nodeHolder2 = getFakeNodeHolder(child2)
        router = object : TestRouter() {
            override val holders: Set<NodeHolder<*>>
                get() = setOf(nodeHolder1, nodeHolder2)
        }
    }

    @Test
    fun getState() {
        assertStateEquals(mapOf(router.javaClass.simpleName to NodeState(null, setOf())), router.getState())

        router.attachTestNode(nodeHolder1)
        assertStateEquals(mapOf(
                router.javaClass.simpleName to NodeState(null, setOf(nodeHolder1.javaClass.simpleName)),
                child1.javaClass.simpleName to NodeState(null, emptySet())), router.getState())

        router.attachTestNode(nodeHolder2)
        assertStateEquals(mapOf(
                router.javaClass.simpleName to NodeState(null, setOf(nodeHolder1.javaClass.simpleName, nodeHolder2.javaClass.simpleName)),
                child1.javaClass.simpleName to NodeState(null, emptySet()),
                child2.javaClass.simpleName to NodeState(null, emptySet())), router.getState())

        router.detachTestNode(nodeHolder1)
        assertStateEquals(mapOf(
                router.javaClass.simpleName to NodeState(null, setOf(nodeHolder2.javaClass.simpleName)),
                child2.javaClass.simpleName to NodeState(null, emptySet())), router.getState())

        router.detachTestNode(nodeHolder2)
        assertStateEquals(mapOf(router.javaClass.simpleName to NodeState(null, setOf())), router.getState())

    }

    @Test
    fun attachNode() {
        router.attachTestNode(nodeHolder1)
        assertChildrenEquals(mapOf(child1.javaClass to child1), router.testChildren())

        router.attachTestNode(nodeHolder2)
        assertChildrenEquals(mapOf(child1.javaClass to child1, child2.javaClass to child2), router.testChildren())
    }

    @Test(expected = RouterAlreadyAttachedException::class)
    fun attachNode_routerAttachedException() {
        router.attachTestNode(nodeHolder1)
        router.attachTestNode(nodeHolder1)
    }

    @Test
    fun detachNode() {
        val nodeHolder = spy(nodeHolder1)
        val testRouter = object : TestRouter() {
            override val holders: Set<NodeHolder<*>>
                get() = setOf(nodeHolder)
        }

        testRouter.detachTestNode(nodeHolder)
        verify(nodeHolder, Times(0)).destroy()
        Assert.assertTrue(testRouter.testChildren().size == 0)

        testRouter.attachTestNode(nodeHolder)
        Assert.assertTrue(testRouter.testChildren().size == 1)
        testRouter.detachTestNode(nodeHolder)
        verify(nodeHolder, Times(1)).destroy()
        Assert.assertTrue(testRouter.testChildren().size == 0)
    }

    @Test
    fun detachAllChildren() {
        router.attachTestNode(nodeHolder1)
        router.attachTestNode(nodeHolder2)
        Assert.assertEquals(2, router.testChildren().size)

        router.detachAllChildren()
        Assert.assertEquals(0, router.testChildren().size)
    }

    @Test
    fun setState() {
        val state = NodeState(null, setOf(nodeHolder1.javaClass.simpleName, nodeHolder2.javaClass.simpleName))
        router.setState(state)
        val routerState = router.getState()[router.javaClass.simpleName] ?: throw AssertionFailedError("Router state not found")
        Assert.assertTrue(routerState.contains(nodeHolder1.javaClass))
        Assert.assertTrue(routerState.contains(nodeHolder2.javaClass))
    }

    @Test
    fun onBackPressed() {
        router.attachTestNode(nodeHolder1)
        router.attachTestNode(nodeHolder2)

        val backStack = Stack<String>()
        backStack.push(router.javaClass.simpleName)
        val activityState = ActivityState(backStack, emptyMap())
        var routerBackHandler = spy(getBackHandler(activityState, true))
        var backHandler1 = spy(getBackHandler(activityState, false))
        var backHandler2 = spy(getBackHandler(activityState, false))
        child1.setBackHandler(backHandler1)
        child2.setBackHandler(backHandler2)
        router.setBackHandler(routerBackHandler)

        router.onBackPressed()
        verify(backHandler1, Times(0)).onBackPressed()
        verify(backHandler2, Times(0)).onBackPressed()
        verify(routerBackHandler, Times(1)).onBackPressed()



        routerBackHandler = spy(getBackHandler(activityState, true))
        backHandler1 = spy(getBackHandler(activityState, false))
        backHandler2 = spy(getBackHandler(activityState, true))
        child1.setBackHandler(backHandler1)
        child2.setBackHandler(backHandler2)
        router.setBackHandler(routerBackHandler)

        backStack.clear()
        backStack.push(router.javaClass.simpleName)
        backStack.push(child2.javaClass.simpleName)

        router.onBackPressed()
        verify(backHandler1, Times(0)).onBackPressed()
        verify(backHandler2, Times(1)).onBackPressed()
        verify(routerBackHandler, Times(0)).onBackPressed()



        routerBackHandler = spy(getBackHandler(activityState, true))
        backHandler1 = spy(getBackHandler(activityState, true))
        backHandler2 = spy(getBackHandler(activityState, true))
        child1.setBackHandler(backHandler1)
        child2.setBackHandler(backHandler2)
        router.setBackHandler(routerBackHandler)

        backStack.clear()
        backStack.push(router.javaClass.simpleName)
        backStack.push(child2.javaClass.simpleName)
        backStack.push(child1.javaClass.simpleName)

        router.onBackPressed()
        verify(backHandler1, Times(1)).onBackPressed()
        verify(backHandler2, Times(0)).onBackPressed()
        verify(routerBackHandler, Times(0)).onBackPressed()

        router.onBackPressed()
        verify(backHandler1, Times(1)).onBackPressed()
        verify(backHandler2, Times(1)).onBackPressed()
        verify(routerBackHandler, Times(0)).onBackPressed()

        router.onBackPressed()
        verify(backHandler1, Times(1)).onBackPressed()
        verify(backHandler2, Times(1)).onBackPressed()
        verify(routerBackHandler, Times(1)).onBackPressed()

    }

    @Test
    fun removeFromBackStack() {
    }

    private fun getFakeNodeHolder(router: Router): NodeHolder<*> {
        return object : NodeHolder<Router>() {

            override fun build(): Router {
                this.router = router
                return router
            }

            override fun destroy() {
                this.router = null
            }
        }
    }

    private fun assertStateEquals(expected: Map<String, NodeState>, actual: Map<String, NodeState>) {
        if (expected.size != actual.size) throw AssertionError("State map size is different")
        expected.forEach { entry ->
            if (!actual.containsKey(entry.key) || actual[entry.key] != entry.value) {
                throw AssertionError("State map not match: \n expected $expected \n actual $actual")
            }
        }
    }

    private fun assertChildrenEquals(expected: Map<Class<out Router>, Router>, actual: Map<Class<out Router>, Router>) {
        if (expected.size != actual.size) throw AssertionError("State map size is different")
        expected.forEach { entry ->
            if (!actual.containsKey(entry.key) || actual[entry.key] != entry.value) {
                throw AssertionError("State map not match: \n expected $expected \n actual $actual")
            }
        }
    }

    private fun getBackHandler(activityState: ActivityState, isHandlingBackStack: Boolean): BackHandler {
        return object : BackHandler {
            override fun onBackPressed(): Boolean = isHandlingBackStack

            override fun isLastInBackStack(_class: Class<out Router>): Boolean = activityState.isLastInBackStack(_class)

            override fun popLastInBackStack() {
                activityState.popLastInBackStack()
            }

            override fun removeFromBackStack(_class: Class<out Router>) {
                activityState.removeFromBackStack(_class)
            }
        }
    }
}