package com.levnovikov.feature_promo.promo_list.di

import com.levnovikov.feature_promo.promo_list.PromoListNodeHolder
import com.levnovikov.feature_promo.promo_list.PromoListPresenter
import com.levnovikov.feature_promo.promo_list.PromoListRouter
import com.levnovikov.feature_promo.promo_list.PromoListView
import com.levnovikov.feature_promo.promo_list.dependency.PromoListDependency

import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@PromoListScope
@Component(dependencies = [(PromoListDependency::class)], modules = [(PromoListComponent.PromoListModule::class)])
interface PromoListComponent {

    fun inject(view: PromoListView)

    fun router(): PromoListRouter

    fun inject(promoListBuilder: PromoListNodeHolder)

    @Module
    class PromoListModule(private val view: PromoListView) {

        @Provides
        @PromoListScope
        internal fun provideView(): PromoListView {
            return view
        }

        @Provides
        @PromoListScope
        internal fun providePresenter(): PromoListPresenter {
            return view
        }
    }
}
