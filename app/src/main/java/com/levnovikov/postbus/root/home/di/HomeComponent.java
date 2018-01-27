package com.levnovikov.postbus.root.home.di;

import android.view.LayoutInflater;

import com.levnovikov.feature_car_animation.dependency.CarAnimDependency;
import com.levnovikov.feature_map.dependency.MapDependency;
import com.levnovikov.feature_map.dependency.MapSetter;
import com.levnovikov.postbus.root.home.HomeActivity;
import com.levnovikov.postbus.root.home.HomeView;
import com.levnovikov.feature_map.dependency.MapProvider;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor;
import com.levnovikov.system_base.base_di.ActivityComponent;
import com.levnovikov.system_base.base_di.ComponentBuilder;

import dagger.Subcomponent;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@HomeScope
@Subcomponent(modules = { HomeModule.class })
public interface HomeComponent extends ActivityComponent, MapDependency, CarAnimDependency {

    void inject(HomeActivity homeActivity);

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder {
        HomeComponent.Builder homeModule(HomeModule module);
        HomeComponent build();
    }

    LayoutInflater inflater();
    HomeView homeView();
    BookingExtraInteractor.Listener bookingListener();
    MapSetter mapSetter();
    MapProvider mapProvider();
}
