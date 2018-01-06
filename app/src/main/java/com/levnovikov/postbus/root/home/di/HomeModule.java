package com.levnovikov.postbus.root.home.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.levnovikov.feature_map.MapBuilder;
import com.levnovikov.feature_map.MapInteractor;
import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent;
import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.HomeActivity;
import com.levnovikov.postbus.root.home.HomeInteractor;
import com.levnovikov.postbus.root.home.HomeView;
import com.levnovikov.postbus.root.home.allocating.AllocatingBuilder;
import com.levnovikov.postbus.root.home.prebooking.PrebookingBuilder;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor;
import com.levnovikov.stream_state.AppState;
import com.levnovikov.stream_state.AppStateStreamProvider;
import com.levnovikov.system_base.state.ActivityState;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@Module
public class HomeModule {

    private final HomeActivity activity;
    private final ActivityState activityState;

    public HomeModule(HomeActivity activity, ActivityState activityState) {
        this.activity = activity;
        this.activityState = activityState;
    }

    @HomeScope
    @Provides
    Context provideContext() {
        return activity;
    }

    @HomeScope
    @Provides
    PrebookingBuilder providePrebookingBuilder(HomeComponent component) {
        return new PrebookingBuilder(component);
    }

    @HomeScope
    @Provides
    AllocatingBuilder provideAllocatingBuilder(LayoutInflater inflater, HomeView parent, HomeComponent component) {
        return new AllocatingBuilder(inflater, parent, component);
    }

    @HomeScope
    @Provides
    MapBuilder provideMapBuilder(LayoutInflater inflater, HomeView parent, HomeComponent component) {
        return new MapBuilder(inflater, parent, component);
    }

    @HomeScope
    @Provides
    HomeView provideView(LayoutInflater inflater) {
        return (HomeView) inflater.inflate(R.layout.home_view, null, true);
    }

    @HomeScope
    @Provides
    Observable<AppState> provideAppStateStream(AppStateStreamProvider provider) {
        return provider.provideAppStateStream();
    }

    @HomeScope
    @Provides
    Observable<MapLifecycleEvent> provideMapLifecycleStream() {
        return activity.getMapLifecycleStream();
    }

    @HomeScope
    @Provides
    LayoutInflater provideInflater() {
        return activity.getLayoutInflater();
    }

    @HomeScope
    @Provides
    BookingExtraInteractor.Listener provideBookingListener(HomeInteractor interactor) {
        return interactor;
    }

    @HomeScope
    @Provides
    MapInteractor.OnMapInitialized provideOnMapInitialized(HomeInteractor interactor) {
        return interactor;
    }

    @HomeScope
    @Provides
    ActivityState provideActivityState() {
        return activityState;
    }
}
