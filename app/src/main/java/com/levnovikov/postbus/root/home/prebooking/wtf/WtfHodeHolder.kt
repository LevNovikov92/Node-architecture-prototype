package com.levnovikov.postbus.root.home.prebooking.wtf

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.HomeView
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.ViewNodeHolder
import com.levnovikov.system_base.base_di.ActivityComponent
import com.levnovikov.system_base.node_state.ActivityState
import com.levnovikov.system_base.node_state.NodeState
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Inject
import javax.inject.Scope

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */


/** DI **/

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class WtfScope

interface WtfDependencies : ActivityComponent {

}

@WtfScope
@Component(dependencies = [WtfDependencies::class], modules = [WtfComponent.WtfModule::class])
interface WtfComponent : ActivityComponent {

    @Module
    class WtfModule {

    }

    @Component.Builder
    interface Builder {
        fun build(): WtfComponent
        fun dependency(WtfDependencies: WtfDependencies): Builder
        @BindsInstance
        fun view(view: WtfView): Builder
    }

    fun inject(nodeHolder: WtfNodeHolder)
    fun inject(nodeHolder: WtfView)
    fun router(): WtfRouter
}

@WtfScope
class WtfRouter @Inject constructor() : Router() {
    override val holders: Set<NodeHolder<*>>
        get() = TODO("not implemented")

    //TODO implement state logic here if it's needed
}

@WtfScope
class WtfInteractor @Inject constructor(router: WtfRouter, activityState: ActivityState) :
        Interactor<WtfRouter>(router, activityState) {

    override fun onGetActive() {
        super.onGetActive()
        TODO() //add business logic here
    }
}

class WtfNodeHolder(
        inflater: LayoutInflater,
        parent: HomeView,
        private val dependencies: WtfDependencies) :
        ViewNodeHolder<WtfView, WtfRouter>(inflater, parent) {

    override fun build(): WtfRouter {
        val view = buildView()
        val component = buildDIComponent(view)
        component.inject(this)
        component.inject(view)
        attachView()
        return component.router()
    }

    private fun buildDIComponent(view: WtfView): WtfComponent =
            DaggerWtfComponent.builder() //TODO build DI here
                    .dependency(dependencies)
                    .view(view)
                    .build()

    override val layout: Int = R.layout.wtf_view
}

class WtfView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var interactor: WtfInteractor

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        interactor.onGetActive()
    }
}
