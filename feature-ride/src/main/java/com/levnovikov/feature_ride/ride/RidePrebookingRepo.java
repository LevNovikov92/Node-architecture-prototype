package com.levnovikov.feature_ride.ride;

import com.example.core_booking.PrebookingRepo;
import com.example.core_booking.geo.Point;

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

public class RidePrebookingRepo extends PrebookingRepo<RidePrebookingData> {

    private RidePrebookingData data;

    public RidePrebookingRepo(RidePrebookingData ridePrebookingData) {
        this.data = ridePrebookingData;
    }

    public BehaviorField<Point> pickupPoint = new BehaviorField<Point>() {
        @Override
        void save(Point entity) {
            final RidePrebookingData modifiedData = data.copy();
            modifiedData.pickUpPoint = entity;
            data = modifiedData;
        }
    };

    public BehaviorField<Point> dropOffPoint = new BehaviorField<Point>() {
        @Override
        void save(Point entity) {
            final RidePrebookingData modifiedData = data.copy();
            modifiedData.dropOffPoint = entity;
            data = modifiedData;
        }
    };

    public BehaviorField<Integer> carType = new BehaviorField<Integer>() {
        @Override
        void save(Integer entity) {
            final RidePrebookingData modifiedData = data.copy();
            modifiedData.carType = entity;
            data = modifiedData;
        }
    };

    public BehaviorField<String> promo = new BehaviorField<String>() {
        @Override
        void save(String entity) {
            final RidePrebookingData modifiedData = data.copy();
            modifiedData.promoCode = entity;
            data = modifiedData;
        }
    };

    @Override
    protected RidePrebookingData getData() {
        return data.copy();
    }
}
