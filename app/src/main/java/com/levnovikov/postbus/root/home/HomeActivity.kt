package com.levnovikov.postbus.root.home

import android.os.Bundle
import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent
import com.levnovikov.postbus.deeplinks.DeeplinkParser
import com.levnovikov.postbus.deeplinks.DeeplinkParserImpl
import com.levnovikov.postbus.root.home.di.HomeComponent
import com.levnovikov.postbus.root.home.di.HomeModule
import com.levnovikov.system_base.base_di.SubComponentProvider
import com.levnovikov.system_base.lifecycle.LifecycleActivity
import com.levnovikov.system_base.node_state.ActivityState
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject
import java.net.URI
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

class HomeActivity : LifecycleActivity() {

    @Inject
    lateinit var view: HomeView

    @Inject
    lateinit var interactor: HomeInteractor

    private val deeplinkParser: DeeplinkParser = DeeplinkParserImpl()

    private val mapLifecycleEmitter = ReplaySubject.create<MapLifecycleEvent>()

    val mapLifecycleStream: Observable<MapLifecycleEvent>
        get() = mapLifecycleEmitter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityState: ActivityState? = null
        val deeplink = false
        if (deeplink) {
            activityState = deeplinkParser.parseDeeplink(URI("grab://grab.com/prebooking/extra/options"))
        } else {
            if (savedInstanceState != null) {
                activityState = savedInstanceState.getParcelable(HOME_ACTIVITY_STATE)
            }
        }
        injectDependencies(activityState)
        setContentView(view)
        interactor.restoreState()
        mapLifecycleEmitter.onNext(MapLifecycleEvent.CREATE)
    }

    private fun injectDependencies(activityState: ActivityState?) {
        val app = application
        if (app is SubComponentProvider) {
            ((app as SubComponentProvider).provide(HomeComponent.Builder::class.java) as HomeComponent.Builder)
                    .homeModule(HomeModule(
                            this,
                            activityState ?: ActivityState()))
                    .build()
                    .inject(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(HOME_ACTIVITY_STATE, interactor.getActivityState())
    }

    override fun onBackPressed() {
        if (!interactor.onBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        mapLifecycleEmitter.onNext(MapLifecycleEvent.RESUME)
    }

    override fun onPause() {
        super.onPause()
        mapLifecycleEmitter.onNext(MapLifecycleEvent.PAUSE)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapLifecycleEmitter.onNext(MapLifecycleEvent.DESTROY)
    }

    companion object {
        private const val HOME_ACTIVITY_STATE = "HOME_ACTIVITY_STATE"
    }
}
