package com.levnovikov.feature_promo.promo_list.dependency

import com.levnovikov.feature_promo.domain.Promo

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

interface OnPromoSelectedListener {

    fun onPromoSelected(promo: Promo)

    fun onCancel()
}
