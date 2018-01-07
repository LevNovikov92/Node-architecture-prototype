package com.levnovikov.feature_promo.promo_list.di;

import com.levnovikov.feature_promo.promo_list.PromoListBuilder;
import com.levnovikov.feature_promo.promo_list.PromoListPresenter;
import com.levnovikov.feature_promo.promo_list.PromoListRouter;
import com.levnovikov.feature_promo.promo_list.PromoListView;
import com.levnovikov.feature_promo.promo_list.dependency.PromoListDependency;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@PromoListScope
@Component(dependencies = PromoListDependency.class, modules = PromoListComponent.PromoListModule.class)
public interface PromoListComponent {

    void inject(PromoListView view);

    PromoListRouter router();

    void inject(PromoListBuilder promoListBuilder);

    @Module
    class PromoListModule{

        private final PromoListView view;

        public PromoListModule(PromoListView view) {
            this.view = view;
        }

        @Provides
        @PromoListScope
        PromoListView provideView() {
            return view;
        }

        @Provides
        @PromoListScope
        PromoListPresenter providePresenter() {
            return view;
        }
    }
}
