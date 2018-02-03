package com.levnovikov.feature_promo.promo_list

import com.levnovikov.feature_promo.domain.Promo

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

interface PromoListPresenter {

    fun setData(promoList: List<Promo>)
}
