package com.levnovikov.feature_promo.promo_list

import android.view.LayoutInflater
import android.view.ViewGroup

import com.levnovikov.feature_promo.R
import com.levnovikov.feature_promo.promo_list.dependency.PromoListDependency
import com.levnovikov.feature_promo.promo_list.di.DaggerPromoListComponent
import com.levnovikov.feature_promo.promo_list.di.PromoListComponent
import com.levnovikov.system_base.ViewNodeHolder

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

class PromoListNodeHolder(inflater: LayoutInflater, parent: ViewGroup, private val parentComponent: PromoListDependency) : ViewNodeHolder<PromoListView, PromoListRouter>(inflater, parent) {

    override val layout: Int
        get() = R.layout.promo_list_view

    override fun build(): PromoListRouter {
        val view = buildView()
        val component = DaggerPromoListComponent.builder()
                .promoListDependency(parentComponent)
                .promoListModule(PromoListComponent.PromoListModule(view))
                .build()
        component.inject(this) //TODO check that called in every node
        component.inject(view)
        attachView()
        return component.router()
    }
}
