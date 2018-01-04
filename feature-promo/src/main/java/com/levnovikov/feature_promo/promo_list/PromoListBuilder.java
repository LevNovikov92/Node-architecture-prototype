package com.levnovikov.feature_promo.promo_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.feature_promo.R;
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener;
import com.levnovikov.feature_promo.promo_list.di.DaggerPromoListComponent;
import com.levnovikov.feature_promo.promo_list.di.PromoListComponent;
import com.levnovikov.system_base.ViewBuilder;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

public class PromoListBuilder extends ViewBuilder<PromoListView, PromoListRouter> {

    private final OnPromoSelectedListener listener;

    public PromoListBuilder(LayoutInflater inflater, ViewGroup parent, OnPromoSelectedListener listener) {
        super(inflater, parent);
        this.listener = listener;
    }

    @Override
    public PromoListRouter build() {
        final PromoListView view = buildView();
        final PromoListComponent component = DaggerPromoListComponent.builder()
                .onPromoSelectedListener(listener)
                .promoListModule(new PromoListComponent.PromoListModule(view))
                .build();
        component.inject(view);
        view.onAttachedToWindow();
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.promo_list_view;
    }
}
