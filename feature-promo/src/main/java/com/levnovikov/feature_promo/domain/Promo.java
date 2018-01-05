package com.levnovikov.feature_promo.domain;

import com.google.auto.value.AutoValue;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@AutoValue
public abstract class Promo {
    public abstract String title();
    public abstract int priceOff();

    public static Promo create(String title, int priceOff) {
        return new AutoValue_Promo(title, priceOff);
    }
}
