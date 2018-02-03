package com.levnovikov.postbus.root.home.di

import android.content.Context
import android.view.LayoutInflater

import com.levnovikov.feature_map.MapNodeHolder
import com.levnovikov.feature_map.dependency.MapProvider
import com.levnovikov.feature_map.dependency.MapSetter
import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent
import com.levnovikov.postbus.R
import com.levnovikov.postbus.root.home.HomeActivity
import com.levnovikov.postbus.root.home.HomeInteractor
import com.levnovikov.postbus.root.home.HomeView
import com.levnovikov.postbus.root.home.allocating.AllocatingNodeHolder
import com.levnovikov.postbus.root.home.prebooking.PrebookingNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor
import com.levnovikov.stream_state.AppState
import com.levnovikov.stream_state.AppStateStreamProvider
import com.levnovikov.system_base.lifecycle.Lifecycle
import com.levnovikov.system_base.node_state.ActivityState

import dagger.Module
import dagger.Provides
import io.reactivex.Observable

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

@Module
class HomeModule(private val activity: HomeActivity, private val activityState: ActivityState) {

    @HomeScope
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @HomeScope
    @Provides
    fun provideLifecycle(): Lifecycle {
        return activity
    }

    @HomeScope
    @Provides
    fun providePrebookingBuilder(component: HomeComponent): PrebookingNodeHolder {
        return PrebookingNodeHolder(component)
    }

    @HomeScope
    @Provides
    fun provideAllocatingBuilder(inflater: LayoutInflater, parent: HomeView, component: HomeComponent): AllocatingNodeHolder {
        return AllocatingNodeHolder(inflater, parent, component)
    }

    @HomeScope
    @Provides
    fun provideMapBuilder(inflater: LayoutInflater, parent: HomeView, component: HomeComponent): MapNodeHolder {
        return MapNodeHolder(inflater, parent, component)
    }

    @HomeScope
    @Provides
    fun provideView(inflater: LayoutInflater): HomeView {
        return inflater.inflate(R.layout.home_view, null, true) as HomeView
    }

    @HomeScope
    @Provides
    fun provideAppStateStream(provider: AppStateStreamProvider): Observable<AppState> {
        return provider.provideAppStateStream()
    }

    @HomeScope
    @Provides
    fun provideMapLifecycleStream(): Observable<MapLifecycleEvent> {
        return activity.mapLifecycleStream
    }

    @HomeScope
    @Provides
    fun provideInflater(): LayoutInflater {
        return activity.layoutInflater
    }

    @HomeScope
    @Provides
    fun provideBookingListener(interactor: HomeInteractor): BookingExtraInteractor.Listener {
        return interactor
    }

    @HomeScope
    @Provides
    fun provideMapSetter(interactor: HomeInteractor): MapSetter {
        return interactor
    }

    @HomeScope
    @Provides
    fun provideMapProvider(interactor: HomeInteractor): MapProvider {
        return interactor
    }

    @HomeScope
    @Provides
    fun provideActivizxtyState(): ActivityState {
        return activityState
    }
}
