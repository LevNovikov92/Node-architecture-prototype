package com.levnovikov.feature_map

import android.view.LayoutInflater
import android.view.ViewGroup

import com.levnovikov.feature_map.dependency.MapDependency
import com.levnovikov.feature_map.di.DaggerMapComponent
import com.levnovikov.feature_map.di.MapComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

class MapNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val parentComponent: MapDependency) : ViewNodeHolder<MapView, MapRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.map_view

    override fun build(): MapRouter {
        val view = buildView()
        val component = DaggerMapComponent
                .builder()
                .mapDependency(parentComponent)
                .mapModule(MapComponent.MapModule(view))
                .build()
        component.inject(view)
        attachView()
        return component.router()
    }
}
