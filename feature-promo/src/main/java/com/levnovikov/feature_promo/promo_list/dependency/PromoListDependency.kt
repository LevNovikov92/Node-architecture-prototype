package com.levnovikov.feature_promo.promo_list.dependency

import com.levnovikov.system_base.base_di.ActivityComponent

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

interface PromoListDependency : ActivityComponent {

    fun onPromoSelectedListener(): OnPromoSelectedListener
}
