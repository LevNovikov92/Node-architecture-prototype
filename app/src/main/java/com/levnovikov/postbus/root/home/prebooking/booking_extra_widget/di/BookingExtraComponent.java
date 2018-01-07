package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di;

import android.view.LayoutInflater;

import com.levnovikov.feature_promo.promo_list.PromoListNodeHolder;
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener;
import com.levnovikov.feature_promo.promo_list.dependency.PromoListDependency;
import com.levnovikov.postbus.root.home.HomeView;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraNodeHolder;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraRouter;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraView;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.base_di.ActivityComponent;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
@Component(dependencies = PrebookingComponent.class, modules = BookingExtraComponent.BookingExtraModule.class)
public interface BookingExtraComponent extends ActivityComponent, PromoListDependency {

    void inject(BookingExtraView bookingExtraBuilder);

    BookingExtraRouter router();
    LayoutInflater inflater();

    void inject(BookingExtraNodeHolder bookingExtraBuilder);

    @Module
    class BookingExtraModule {

        private BookingExtraView view;

        public BookingExtraModule(BookingExtraView view) {
            this.view = view;
        }

        @BookingExtraScope
        @Provides
        BookingExtraView provideView() {
            return view;
        }

        @BookingExtraScope
        @Provides
        BookingExtraInteractor.Presenter providePresenter() {
            return view;
        }

        @BookingExtraScope
        @Provides
        OnPromoSelectedListener provideOnPromoSelectedListener(BookingExtraInteractor interactor) {
            return interactor;
        }

        @BookingExtraScope
        @Provides
        PromoListDependency providePromoListDependency(BookingExtraComponent component) {
            return component;
        }

        @BookingExtraScope
        @Provides
        PromoListNodeHolder providePromoListBuilder(LayoutInflater inflater, HomeView parent, PromoListDependency parentComponent) {
            return new PromoListNodeHolder(inflater, parent, parentComponent);
        }
    }
}
