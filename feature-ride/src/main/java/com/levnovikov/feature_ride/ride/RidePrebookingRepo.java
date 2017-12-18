package com.levnovikov.feature_ride.ride;

import com.example.core_booking.PrebookingRepo;

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

public class RidePrebookingRepo extends PrebookingRepo<RidePrebookingData> {

    private RidePrebookingData ridePrebookingData;

    RidePrebookingRepo(RidePrebookingData ridePrebookingData) {
        this.ridePrebookingData = ridePrebookingData;
    }

    @Override
    protected RidePrebookingData getData() {
        return ridePrebookingData.copy();
    }
}
