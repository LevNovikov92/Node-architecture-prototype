package com.levnovikov.feature_map.map_wrapper

import com.example.core_geo.Point
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Author: lev.novikov
 * Date: 3/1/18.
 */

class MapWrapper(private val map: GoogleMap //TODO remove link onDestroy
) : MapInterface {

    private var pickUpMarker: Marker? = null

    private var dropOffMarker: Marker? = null

    override fun setPickUp(point: Point) {
        pickUpMarker = setMarker(point)
    }

    override fun setDropOff(point: Point) {
        dropOffMarker = setMarker(point)
    }

    private fun setMarker(point: Point): Marker {
        val coordinates = LatLng(
                point.coordinates.x,
                point.coordinates.y)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 6f))
        return map.addMarker(
                MarkerOptions()
                        .position(coordinates)
                        .title(point.title))
    }

    override fun clear() {
        if (pickUpMarker != null) {
            pickUpMarker!!.remove()
            pickUpMarker = null
        }

        if (dropOffMarker != null) {
            dropOffMarker!!.remove()
            dropOffMarker = null
        }
    }
}
