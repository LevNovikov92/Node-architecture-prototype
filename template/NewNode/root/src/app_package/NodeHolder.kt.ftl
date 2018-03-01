package ${packageName}

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
annotation class ${className}Scope

interface ${className}Dependencies : ActivityComponent {

}

@${className}Scope
@Component(dependencies = [${className}Dependencies::class], modules = [${className}Component.${className}Module::class])
interface ${className}Component : ActivityComponent {

    @Module
    class ${className}Module {

    }

    @Component.Builder
    interface Builder {
        fun build(): ${className}Component
        fun dependency(${className}Dependencies: ${className}Dependencies): Builder
        @BindsInstance
        fun view(view: ${className}View): Builder
    }

    fun inject(nodeHolder: ${className}NodeHolder)
    fun inject(nodeHolder: ${className}View)
    fun router(): ${className}Router
}

@${className}Scope
class ${className}Router @Inject constructor() : Router() {
    override val holders: Set<NodeHolder<*>>
        get() = TODO("not implemented")

    override fun setState(state: NodeState) {
        TODO("not implemented")
    }

    //TODO implement state logic here if it's needed
}

@${className}Scope
class ${className}Interactor @Inject constructor(router: ${className}Router, activityState: ActivityState) :
        Interactor<${className}Router>(router, activityState) {

    override fun onGetActive() {
        super.onGetActive()
        TODO() //add business logic here
    }
}

class ${className}NodeHolder(
        inflater: LayoutInflater,
        parent: HomeView,
        private val dependencies: ${className}Dependencies) :
        ViewNodeHolder<${className}View, ${className}Router>(inflater, parent) {

    override fun build(): ${className}Router {
        val view = buildView()
        val component = buildDIComponent(view)
        component.inject(this)
        component.inject(view)
        attachView()
        return component.router()
    }

    private fun buildDIComponent(view: ${className}View): ${className}Component =
            Dagger${className}Component.builder() //TODO build DI here
                .dependency(dependencies)
                .view(view)
                .build()

    override val layout: Int = R.layout.${xmlName} 
}

class ${className}View @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var interactor: ${className}Interactor

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        interactor.onGetActive()
    }
}
