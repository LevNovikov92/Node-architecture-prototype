package com.levnovikov.feature_promo.promo_list.di;

import com.levnovikov.feature_promo.promo_list.PromoListRouter;
import com.levnovikov.feature_promo.promo_list.PromoListView;
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener;

import dagger.Component;
import dagger.Module;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@PromoListScope
@Component(dependencies = OnPromoSelectedListener.class, modules = PromoListComponent.PromoListModule.class)
public interface PromoListComponent {

    void inject(PromoListView view);

    PromoListRouter router();

    @Module
    class PromoListModule{

        private final PromoListView view;

        public PromoListModule(PromoListView view) {
            this.view = view;
        }

        @PromoListScope
        PromoListView provideView() {
            return view;
        }
    }
}
