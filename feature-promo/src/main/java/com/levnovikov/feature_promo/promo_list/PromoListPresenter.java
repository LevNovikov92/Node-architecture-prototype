package com.levnovikov.feature_promo.promo_list;

import com.levnovikov.feature_promo.domain.Promo;

import java.util.List;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

public interface PromoListPresenter {

    void setData(List<Promo> promoList);
}
