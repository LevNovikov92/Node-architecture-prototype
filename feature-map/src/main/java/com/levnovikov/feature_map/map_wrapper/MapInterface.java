package com.levnovikov.feature_map.map_wrapper;

import com.example.core_geo.Point;

/**
 * Author: lev.novikov
 * Date: 3/1/18.
 */

public interface MapInterface {

    void setPickUp(Point point);
    void setDropOff(Point point);
    void clear();
}
