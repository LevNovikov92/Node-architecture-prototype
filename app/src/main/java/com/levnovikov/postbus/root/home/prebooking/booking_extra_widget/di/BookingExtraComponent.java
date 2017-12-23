package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di;

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraBuilder;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraRouter;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraView;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
@Component(dependencies = PrebookingComponent.class, modules = BookingExtraComponent.BookingExtraModule.class)
public interface BookingExtraComponent {

    void inject(BookingExtraBuilder bookingExtraBuilder);

    BookingExtraRouter router();

    @Module
    public class BookingExtraModule {

        private BookingExtraView view;

        public BookingExtraModule(BookingExtraView view) {
            this.view = view;
        }

        @BookingExtraScope
        @Provides
        BookingExtraView provideView() {
            return view;
        }
    }
}
