package com.levnovikov.system_base

import com.levnovikov.system_base.exceptions.RouterAlreadyAttachedException
import com.levnovikov.system_base.node_state.NodeState
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.internal.verification.Times

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
    fun detachChildren() {
    }

    @Test
    fun destroyNode() {
    }

    @Test
    fun getHolders() {
    }

    @Test
    fun nodes() {
    }

    @Test
    fun setState() {
    }

    @Test
    fun onBackPressed() {
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
}