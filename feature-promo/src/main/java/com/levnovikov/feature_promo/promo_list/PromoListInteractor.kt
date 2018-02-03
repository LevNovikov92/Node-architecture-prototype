package com.levnovikov.feature_promo.promo_list

import android.os.Parcelable
import com.levnovikov.feature_promo.domain.Promo
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener
import com.levnovikov.feature_promo.promo_list.di.PromoListScope
import com.levnovikov.system_base.BackStateInteractor
import com.levnovikov.system_base.node_state.ActivityState
import java.util.*
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

@PromoListScope
class PromoListInteractor @Inject constructor(
        router: PromoListRouter,
        activityState: ActivityState,
        private val presenter: PromoListPresenter,
        private val listener: OnPromoSelectedListener) : BackStateInteractor<PromoListRouter>(router, activityState) {

    private//Hardcoded data
    val promoList: List<Promo>
        get() {
            val promoList = ArrayList<Promo>()
            for (i in 0..4) {
                promoList.add(Promo("Promo", i + 1))
            }
            return promoList
        }

    override fun onGetActive() {
        super.onGetActive()
        presenter.setData(promoList)
    }

    override fun onBackPressed(): Boolean {
        listener.onCancel()
        return true
    }

    override fun stateData(): Parcelable? = null
}
