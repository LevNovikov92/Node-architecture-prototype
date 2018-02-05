package com.levnovikov.feature_ride.ride

import com.example.core_booking.PrebookingRepo
import com.example.core_geo.Point

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

class RidePrebookingRepo(private var data: RidePrebookingData) : PrebookingRepo<RidePrebookingData>() {

    var pickupPoint: BehaviorField<Point> = object : BehaviorField<Point>() {
        internal override fun save(entity: Point) {
            data = data.copy(pickUpPoint = entity)
        }
    }

    var dropOffPoint: BehaviorField<Point> = object : BehaviorField<Point>() {
        internal override fun save(entity: Point) {
            data = data.copy(dropOffPoint = entity)
        }
    }

    var carType: BehaviorField<Int> = object : BehaviorField<Int>() {
        internal override fun save(entity: Int) {
            data = data.copy(carType = entity)
        }
    }

    var promo: BehaviorField<String> = object : BehaviorField<String>() {
        internal override fun save(entity: String) {
            data = data.copy(promoCode = entity)
        }
    }

    public override fun getData(): RidePrebookingData {
        return data.copy()
    }

    fun setData(data: RidePrebookingData) {
        this.data = data
        data.pickUpPoint?.let { pickupPoint.set(it) }
        data.dropOffPoint?.let { dropOffPoint.set(it) }
        carType.set(data.carType)
        promo.set(data.promoCode)
    }
}
