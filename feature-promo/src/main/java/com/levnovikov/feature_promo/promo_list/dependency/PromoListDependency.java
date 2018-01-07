package com.levnovikov.feature_promo.promo_list.dependency;

import com.levnovikov.system_base.node_state.ActivityState;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

public interface PromoListDependency {

    OnPromoSelectedListener onPromoSelectedListener();
    ActivityState activityState();
}
