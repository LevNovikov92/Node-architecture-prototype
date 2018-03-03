package com.levnovikov.feature_map

import android.content.Context
import android.util.AttributeSet
import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent
import com.levnovikov.feature_map.lifecycle.MapLifecycleListener
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

class MapView : com.google.android.gms.maps.MapView, MapLifecycleListener {

    @Inject
    lateinit var interactor: MapInteractor

    @Inject
    lateinit var mapLifecycleStream: Observable<MapLifecycleEvent>

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        interactor.restoreState()
        initMap()
    }

    private fun initMap() {
        getMapAsync(interactor)
        mapLifecycleStream //TODO unsubscribe
        .subscribe({ event ->
            when (event) {
                MapLifecycleEvent.CREATE -> onCreate(event.bundle)
                MapLifecycleEvent.RESUME -> onResume()
                MapLifecycleEvent.PAUSE -> onPause()
                MapLifecycleEvent.DESTROY -> onDestroy()
            }
        }) { e -> }
    }
}
