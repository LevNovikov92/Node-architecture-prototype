package com.levnovikov.feature_map.map_wrapper;

import com.example.core_geo.Point;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import io.reactivex.annotations.Nullable;

/**
 * Author: lev.novikov
 * Date: 3/1/18.
 */

public class MapWrapper implements MapInterface {

    private GoogleMap map; //TODO remove link onDestroy

    @Nullable
    private Marker pickUpMarker;

    @Nullable
    private Marker dropOffMarker;

    public MapWrapper(final GoogleMap map) {
        this.map = map;
    }

    @Override
    public void setPickUp(Point point) {
        pickUpMarker = setMarker(point);
    }

    @Override
    public void setDropOff(Point point) {
        dropOffMarker = setMarker(point);
    }

    private Marker setMarker(Point point) {
        final LatLng coordinates = new LatLng(
                point.coordinates().x(),
                point.coordinates().y());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 6f));
        return map.addMarker(
                new MarkerOptions()
                        .position(coordinates)
                .title(point.title()));
    }

    @Override
    public void clear() {
        if (pickUpMarker != null) {
            pickUpMarker.remove();
            pickUpMarker = null;
        }

        if (dropOffMarker != null) {
            dropOffMarker.remove();
            dropOffMarker = null;
        }
    }
}
