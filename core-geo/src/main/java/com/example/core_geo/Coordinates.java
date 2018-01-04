package com.example.core_geo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@AutoValue
public abstract class Coordinates implements Parcelable {

    public abstract double x();

    public abstract double y();

    public static Coordinates create(double x, double y) {
        return new AutoValue_Coordinates(x, y);
    }
}
