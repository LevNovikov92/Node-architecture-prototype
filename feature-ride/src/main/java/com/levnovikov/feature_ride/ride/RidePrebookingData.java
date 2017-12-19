package com.levnovikov.feature_ride.ride;


import com.example.core_booking.PrebookingData;

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

public class RidePrebookingData extends PrebookingData {

    public int carType;
    public String promoCode; //TODO implement promo module

    RidePrebookingData copy() {
        final RidePrebookingData data = new RidePrebookingData();
        data.pickUpPoint = pickUpPoint;
        data.dropOffPoint = dropOffPoint;
        data.carType = carType;
        data.promoCode = promoCode;
        return data;
    }
}
