package com.levnovikov.feature_ride.ride;


import android.os.Parcelable;

import com.example.core_geo.Point;
import com.google.auto.value.AutoValue;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

@AutoValue
public abstract class RidePrebookingData implements Parcelable {

    public abstract int carType();
    @Nullable
    public abstract String promoCode(); //TODO implement promo module

    @Nullable
    public abstract Point pickUpPoint();
    @Nullable
    public abstract Point dropOffPoint();

    public static Builder builder() {
        return new AutoValue_RidePrebookingData.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder carType(int carType);
        public abstract Builder promoCode(String promo);
        public abstract Builder pickUpPoint(Point point);
        public abstract Builder dropOffPoint(Point point);
        public abstract RidePrebookingData build();
    }

    abstract Builder toBuilder();

//    RidePrebookingData copy() {
//        final RidePrebookingData data = new RidePrebookingData();
//        data.pickUpPoint = pickUpPoint;
//        data.dropOffPoint = dropOffPoint;
//        data.carType = carType;
//        data.promoCode = promoCode;
//        return data;
//    }
}
