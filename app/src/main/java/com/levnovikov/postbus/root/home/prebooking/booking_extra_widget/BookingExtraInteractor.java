package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import com.levnovikov.feature_promo.domain.Promo;
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
public class BookingExtraInteractor extends Interactor<BookingExtraRouter> implements OnPromoSelectedListener {

    @Inject
    public BookingExtraInteractor(
            Presenter presenter,
            Listener listener,
            BookingExtraRouter router,
            ActivityState activityState) {
        super(router, activityState);
        presenter.getBookingClickStream()
                .subscribe(o -> {
                    listener.onBookClick();
                }, e -> {});

        presenter.getPromoClickStream()
                .subscribe(o -> {
                    router.attachPromoList();
                }, e -> {});
    }

    @Override
    public void onPromoSelected(Promo promo) {
        router.detachPromoList();
    }

    @Override
    public void onCancel() {
        router.detachPromoList();
    }

    public interface Listener {
        void onBookClick();
    }

    public interface Presenter {
        Observable<Object> getBookingClickStream();

        Observable<Object> getPromoClickStream();
    }
}
