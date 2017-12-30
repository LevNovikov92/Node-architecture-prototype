package com.levnovikov.postbus.root.home.prebooking.di;

import android.view.LayoutInflater;

import com.levnovikov.feature_ride.ride.RidePrebookingData;
import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.HomeView;
import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.prebooking.PrebookingBuilder;
import com.levnovikov.postbus.root.home.prebooking.PrebookingInteractor;
import com.levnovikov.postbus.root.home.prebooking.PrebookingRouter;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraBuilder;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetInteractor;
import com.levnovikov.system_base.base_di.ActivityStateComponent;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
@Component(dependencies = HomeComponent.class, modules = PrebookingComponent.PrebookingModule.class)
public interface PrebookingComponent extends ActivityStateComponent {

    void inject(PrebookingBuilder prebookingBuilder);

    PrebookingInteractor interactor();

    @Module(includes = PrebookingModule.Binders.class)
    class PrebookingModule {

        @PrebookingScope
        @Provides
        RidePrebookingData provideDefaultPrebookingData() {
            return RidePrebookingData.builder()
                    .carType(0)
                    .dropOffPoint(null)
                    .pickUpPoint(null)
                    .promoCode("")
                    .build();
        }

        @PrebookingScope
        @Provides
        RidePrebookingRepo providePrebookingRepo(RidePrebookingData defaultData) {
            return new RidePrebookingRepo(defaultData);
        }

        @PrebookingScope
        @Provides
        PoiWidgetBuilder providePoiWidgetBuilder(LayoutInflater inflater, HomeView parent, PrebookingComponent component) {
            return new PoiWidgetBuilder(inflater, parent, component);
        }

        @PrebookingScope
        @Provides
        PoiSelectorBuilder providePoiSelectorBuilder(LayoutInflater inflater, HomeView parent, PrebookingComponent component) {
            return new PoiSelectorBuilder(inflater, parent, component);
        }

        @PrebookingScope
        @Provides
        BookingExtraBuilder provideBookingExtraBuilder(LayoutInflater inflater, HomeView parent, PrebookingComponent component) {
            return new BookingExtraBuilder(inflater, parent, component);
        }

        @PrebookingScope
        @Provides
        CarTypeSelectorBuilder provideCarType(LayoutInflater inflater, HomeView parent, PrebookingComponent component) {
            return new CarTypeSelectorBuilder(inflater, parent, component);
        }

        @Module
        public interface Binders {

            @Binds
            PoiSelectorInteractor.PoiSelectionListener provideSelectionListener(PrebookingInteractor interactor);

            @Binds
            PoiWidgetInteractor.PoiClickListener providePoiClickListener(PrebookingInteractor interactor);

            @Binds
            CarTypeSelectorInteractor.Listener provideCarTypeClickListener(PrebookingInteractor interactor);
        }
    }

    PrebookingRouter getRouter();

    RidePrebookingRepo prebookingRepo();

    PoiSelectorInteractor.PoiSelectionListener selectionListener();

    PoiWidgetInteractor.PoiClickListener clickListener();

    CarTypeSelectorInteractor.Listener carTypeListener();

    BookingExtraInteractor.Listener bookingListener();

    LayoutInflater inflater();

    HomeView homeView();
}
