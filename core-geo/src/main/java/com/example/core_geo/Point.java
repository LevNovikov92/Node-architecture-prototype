package com.example.core_geo;

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

public class Point {

    public final Coordinates coordinates;

    public final String title;

    public Point(Coordinates coordinates, String title) {
        this.coordinates = coordinates;
        this.title = title;
    }
}
