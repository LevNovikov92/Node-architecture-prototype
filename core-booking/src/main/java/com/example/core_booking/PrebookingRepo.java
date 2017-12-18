package com.example.core_booking;

/**
 * Created by lev.novikov
 * Date: 18/12/17.
 */

public abstract class PrebookingRepo<Data extends PrebookingData> {

    protected abstract Data getData();
}
