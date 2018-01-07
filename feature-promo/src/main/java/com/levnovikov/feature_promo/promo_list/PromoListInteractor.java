package com.levnovikov.feature_promo.promo_list;

import android.os.Parcelable;

import com.levnovikov.feature_promo.domain.Promo;
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener;
import com.levnovikov.feature_promo.promo_list.di.PromoListScope;
import com.levnovikov.system_base.BackStateInteractor;
import com.levnovikov.system_base.node_state.ActivityState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@PromoListScope
public class PromoListInteractor extends BackStateInteractor<PromoListRouter> {

    private final PromoListPresenter presenter;
    private OnPromoSelectedListener listener;

    @Inject
    PromoListInteractor(
            PromoListRouter router,
            ActivityState activityState,
            PromoListPresenter presenter,
            OnPromoSelectedListener listener) {
        super(router, activityState);
        this.presenter = presenter;
        this.listener = listener;
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

    @Override
    public boolean onBackPressed() {
        listener.onCancel();
        return true;
    }

    @Override
    public Parcelable getStateData() {
        return null;
    }
}
