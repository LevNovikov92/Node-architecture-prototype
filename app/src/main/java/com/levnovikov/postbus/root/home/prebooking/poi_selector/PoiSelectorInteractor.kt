package com.levnovikov.postbus.root.home.prebooking.poi_selector

import android.os.Parcelable
import com.example.core_geo.Point
import com.example.core_location.PoiSuggestionProvider
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorScope
import com.levnovikov.system_base.BackStateInteractor
import com.levnovikov.system_base.lifecycle.Lifecycle
import com.levnovikov.system_base.node_state.ActivityState
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@PoiSelectorScope
class PoiSelectorInteractor @Inject
constructor(
        private val poiProvider: PoiSuggestionProvider,
        private val presenter: Presenter,
        router: PoiSelectorRouter,
        activityState: ActivityState,
        private val listener: PoiSelectionListener,
        private val lifecycle: Lifecycle) : BackStateInteractor<PoiSelectorRouter>(router, activityState) {
    override fun onSaveData(): Parcelable? = null

    interface PoiSelectionListener {
        fun onPoiSelected(point: Point)
        fun onPoiSelectionCanceled()
    }

    override fun onGetActive() {
        super.onGetActive()
        lifecycle.subscribeUntilDestroy(presenter.placeTitleStream()
                .subscribe({ title -> poiProvider.updatePlace(title) }
                ) { /*handle error*/ error -> })

        lifecycle.subscribeUntilDestroy(poiProvider.poiStream
                .subscribe({ poiList -> presenter.updateSuggestions(poiList) }
                ) { /*handle error*/ error -> })

        lifecycle.subscribeUntilDestroy(presenter.selectedPoi()
                .subscribe({ point -> listener.onPoiSelected(point) }) { error -> })
    }

    override fun onBackPressed(): Boolean {
        listener.onPoiSelectionCanceled()
        return true
    }

    interface Presenter {
        fun selectedPoi(): Observable<Point>
        fun placeTitleStream(): Observable<String>
        fun updateSuggestions(poiList: List<Point>)
    }
}
