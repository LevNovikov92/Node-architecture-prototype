package com.levnovikov.feature_ride.ride;

import com.example.core_booking.PrebookingRepo;
import com.example.core_geo.Point;

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
            data = data.toBuilder().pickUpPoint(entity).build();
        }
    };

    public BehaviorField<Point> dropOffPoint = new BehaviorField<Point>() {
        @Override
        void save(Point entity) {
            data = data.toBuilder().dropOffPoint(entity).build();
        }
    };

    public BehaviorField<Integer> carType = new BehaviorField<Integer>() {
        @Override
        void save(Integer entity) {
            data = data.toBuilder().carType(entity).build();
        }
    };

    public BehaviorField<String> promo = new BehaviorField<String>() {
        @Override
        void save(String entity) {
            data = data.toBuilder().promoCode(entity).build();
        }
    };

    @Override
    public RidePrebookingData getData() {
        return data.toBuilder().build();
    }

    public void setData(RidePrebookingData data) {
        this.data = data;
        pickupPoint.set(data.pickUpPoint());
        dropOffPoint.set(data.dropOffPoint());
        carType.set(data.carType());
        promo.set(data.promoCode());
    }
}
