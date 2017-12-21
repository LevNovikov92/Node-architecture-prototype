package com.example.core_location;

import com.example.core_geo.Point;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

public interface PoiSuggestionProvider {

    Observable<List<Point>> getPoiStream();

    void updatePlace(String placeName);
}
