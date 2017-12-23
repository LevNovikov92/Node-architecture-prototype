package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraScope;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
public class BookingExtraInteractor implements Interactor {

    @Inject
    public BookingExtraInteractor(Presenter presenter, Listener listener) {
        presenter.getClickStream()
                .subscribe(o -> {
                    listener.onBookClick();
                }, e -> {});
    }

    @Override
    public void onGetActive() {

    }

    public interface Listener {
        void onBookClick();
    }

    public interface Presenter {
        Observable<Object> getClickStream();
    }
}
