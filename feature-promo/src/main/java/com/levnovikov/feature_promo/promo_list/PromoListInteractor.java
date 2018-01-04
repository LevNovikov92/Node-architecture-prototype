package com.levnovikov.feature_promo.promo_list;

import com.levnovikov.feature_promo.domain.Promo;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

public class PromoListInteractor extends Interactor<PromoListRouter> {

    private final PromoListPresenter presenter;

    public PromoListInteractor(
            PromoListRouter router,
            ActivityState activityState,
            PromoListPresenter presenter) {
        super(router, activityState);
        this.presenter = presenter;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        presenter.setData(getPromoList());
    }

    private List<Promo> getPromoList() {
        final List<Promo> promoList = new ArrayList<>(); //Hardcoded data
        for (int i = 0; i < 5; i++) {
            promoList.add(Promo.create("Promo", i + 1));
        }
        return promoList;
    }
}
