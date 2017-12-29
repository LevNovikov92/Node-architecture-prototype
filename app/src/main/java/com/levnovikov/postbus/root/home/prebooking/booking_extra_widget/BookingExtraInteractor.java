package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

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
public class BookingExtraInteractor extends Interactor<BookingExtraRouter> {

    @Inject
    public BookingExtraInteractor(
            Presenter presenter,
            Listener listener,
            BookingExtraRouter router,
            ActivityState activityState) {
        super(router, activityState);
        presenter.getClickStream()
                .subscribe(o -> {
                    listener.onBookClick();
                }, e -> {});
    }

    public interface Listener {
        void onBookClick();
    }

    public interface Presenter {
        Observable<Object> getClickStream();
    }
}
