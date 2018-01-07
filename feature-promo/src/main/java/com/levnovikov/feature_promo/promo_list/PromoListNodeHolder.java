package com.levnovikov.feature_promo.promo_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.feature_promo.R;
import com.levnovikov.feature_promo.promo_list.dependency.PromoListDependency;
import com.levnovikov.feature_promo.promo_list.di.DaggerPromoListComponent;
import com.levnovikov.feature_promo.promo_list.di.PromoListComponent;
import com.levnovikov.system_base.ViewNodeHolder;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

public class PromoListNodeHolder extends ViewNodeHolder<PromoListView, PromoListRouter> {

    private final PromoListDependency parentComponent;

    public PromoListNodeHolder(LayoutInflater inflater, ViewGroup parent, PromoListDependency parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public PromoListRouter build() {
        final PromoListView view = buildView();
        final PromoListComponent component = DaggerPromoListComponent.builder()
                .promoListDependency(parentComponent)
                .promoListModule(new PromoListComponent.PromoListModule(view))
                .build();
        component.inject(this); //TODO check that called in every node
        component.inject(view);
        attachView();
        return component.router();
    }

    @Override
    public int getLayout() {
        return R.layout.promo_list_view;
    }
}
