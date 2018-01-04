package com.levnovikov.feature_promo.domain;

import com.google.auto.value.AutoValue;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@AutoValue
public abstract class Promo {
    public abstract String getTitle();
    public abstract int getPriceOff();

    public static Promo create(String title, int priceOff) {
        return AutoValue_Promo.create(title, priceOff);
    }
}
