package com.levnovikov.postbus.root.home.prebooking.car_type_selector.template

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.system_base.BR
import com.levnovikov.postbus.R
import com.levnovikov.postbus.databinding.TemplateViewBinding
import com.levnovikov.system_base.BindingNodeHolder
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.NodeHolder
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.base_di.ActivityComponent
import com.levnovikov.system_base.node_state.ActivityState
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
annotation class TemplateScope

interface TemplateDependencies : ActivityComponent {

}

@Module
class TemplateModule {

}

@TemplateScope
@Component(dependencies = [TemplateDependencies::class], modules = [TemplateModule::class])
interface TemplateComponent : ActivityComponent {

    @Component.Builder
    interface Builder {
        fun build(): TemplateComponent
        fun dependency(TemplateDependencies: TemplateDependencies): Builder
    }

    fun inject(nodeHolder: TemplateNodeHolder)
    fun router(): TemplateRouter
}

@TemplateScope
class TemplateRouter @Inject constructor() : Router() {
    override val holders: Set<NodeHolder<*>>
        get() = TODO("not implemented")

    //TODO implement state logic here if it's needed
}

@TemplateScope
class TemplateInteractor @Inject constructor(router: TemplateRouter, activityState: ActivityState) :
        Interactor<TemplateRouter>(router, activityState) {

}

@TemplateScope
class TemplateVM @Inject constructor(
        private val interactor: TemplateInteractor
) {

    init {
        if (interactor.hasSavedState()) interactor.restoreState()
    }

}

class TemplateNodeHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val dependencies: TemplateDependencies) :
        BindingNodeHolder<TemplateRouter, TemplateViewBinding>(inflater, parent) {

    @Inject
    lateinit var vm: TemplateVM

    override fun build(): TemplateRouter {
        val component = buildDIComponent()
        component.inject(this)
        buildAndAttachView(vm, BR.vm)
        return component.router()
    }

    private fun buildDIComponent(): TemplateComponent =
            DaggerTemplateComponent.builder() //TODO build DI here
                    .dependency(dependencies)
                    .build()

    override val layout: Int = R.layout.template_view
}
