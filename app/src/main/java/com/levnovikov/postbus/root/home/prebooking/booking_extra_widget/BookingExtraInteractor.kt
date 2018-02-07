package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget

import com.levnovikov.feature_promo.domain.Promo
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraScope
import com.levnovikov.system_base.Interactor
import com.levnovikov.system_base.node_state.ActivityState
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
class BookingExtraInteractor @Inject
constructor(
        presenter: Presenter,
        listener: Listener,
        router: BookingExtraRouter,
        activityState: ActivityState) : Interactor<BookingExtraRouter>(router, activityState), OnPromoSelectedListener {

    init {
        presenter.bookingClickStream()
                .subscribe({ _ -> listener.onBookClick() }) { e -> }

        presenter.promoClickStream()
                .subscribe({ _ -> router.attachPromoList() }) { e -> }
    }

    override fun onPromoSelected(promo: Promo) {
        router.detachPromoList()
    }

    override fun onCancel() {
        router.detachPromoList()
    }

    interface Listener {
        fun onBookClick()
    }

    interface Presenter {
        fun bookingClickStream(): Observable<Any>

        fun promoClickStream(): Observable<Any>
    }
}
