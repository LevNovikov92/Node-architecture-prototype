package com.example.core_geo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@AutoValue
public abstract class Coordinates implements Parcelable {

    public abstract float x();

    public abstract float y();

    public static Coordinates create(float x, float y) {
        return new AutoValue_Coordinates(x, y);
    }
}
