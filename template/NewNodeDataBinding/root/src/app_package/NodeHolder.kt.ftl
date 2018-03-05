package ${packageName}

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.system_base.BR
import com.levnovikov.postbus.R
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


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ${className}Scope

interface ${className}Dependencies : ActivityComponent {

}

@Module
class ${className}Module {

}

@${className}Scope
@Component(dependencies = [${className}Dependencies::class], modules = [${className}Module::class])
interface ${className}Component : ActivityComponent {

    @Component.Builder
    interface Builder {
        fun build(): ${className}Component
        fun dependency(${className}Dependencies: ${className}Dependencies): Builder
    }

    fun inject(nodeHolder: ${className}NodeHolder)
    fun router(): ${className}Router
}

@${className}Scope
class ${className}Router @Inject constructor() : Router() {
    override val holders: Set<NodeHolder<*>>
        get() = TODO("not implemented")

    //TODO implement state logic here if it's needed
}

@${className}Scope
class ${className}Interactor @Inject constructor(router: ${className}Router, activityState: ActivityState) :
        Interactor<${className}Router>(router, activityState) {

}

@${className}Scope
class ${className}VM @Inject constructor(
        private val interactor: ${className}Interactor
) {

    init {
        if (interactor.hasSavedState()) interactor.restoreState() //TODO remove if state restoration not required
    }

}

class ${className}NodeHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val dependencies: ${className}Dependencies) :
        BindingNodeHolder<${className}Router, ${className}ViewBinding>(inflater, parent) {

    @Inject
    lateinit var vm: ${className}VM

    override fun build(): ${className}Router {
        val component = buildDIComponent()
        component.inject(this)
        buildAndAttachView(vm, BR.vm)
        return component.router()
    }

    private fun buildDIComponent(): ${className}Component =
            Dagger${className}Component.builder()
                    .dependency(dependencies)
                    .build()

    override val layout: Int = R.layout.${xmlName}
}

