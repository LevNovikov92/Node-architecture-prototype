package com.levnovikov.feature_promo.promo_list.dependency

import com.levnovikov.system_base.node_state.ActivityState

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

interface PromoListDependency {

    fun onPromoSelectedListener(): OnPromoSelectedListener
    fun activityState(): ActivityState
}
