package com.example.core_geo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

@AutoValue
public abstract class Point implements Parcelable {

    public abstract Coordinates coordinates();

    public abstract String title();

    public static Point create(Coordinates coordinates, String title) {
        return new AutoValue_Point(coordinates, title);
    }
}
