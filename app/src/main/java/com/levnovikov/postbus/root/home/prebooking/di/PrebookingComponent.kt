package com.levnovikov.postbus.root.home.prebooking.di

import android.view.LayoutInflater
import com.levnovikov.feature_map.dependency.MapProvider
import com.levnovikov.feature_ride.ride.RidePrebookingData
import com.levnovikov.feature_ride.ride.RidePrebookingRepo
import com.levnovikov.postbus.root.home.HomeView
import com.levnovikov.postbus.root.home.di.HomeComponent
import com.levnovikov.postbus.root.home.prebooking.PrebookingInteractor
import com.levnovikov.postbus.root.home.prebooking.PrebookingNodeHolder
import com.levnovikov.postbus.root.home.prebooking.PrebookingRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorInteractor
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorInteractor
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetInteractor
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetNodeHolder
import com.levnovikov.system_base.base_di.ActivityComponent
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
@Component(dependencies = [(HomeComponent::class)], modules = [(PrebookingComponent.PrebookingModule::class)])
interface PrebookingComponent : ActivityComponent {

    fun inject(prebookingBuilder: PrebookingNodeHolder)

    fun interactor(): PrebookingInteractor
    fun mapProvider(): MapProvider

    @Module(includes = [(PrebookingModule.Binders::class)])
    class PrebookingModule {

        @PrebookingScope
        @Provides
        internal fun provideDefaultPrebookingData(): RidePrebookingData {
            return RidePrebookingData(0, "", null, null)
        }

        @PrebookingScope
        @Provides
        internal fun providePrebookingRepo(defaultData: RidePrebookingData): RidePrebookingRepo {
            return RidePrebookingRepo(defaultData)
        }

        @PrebookingScope
        @Provides
        internal fun providePoiWidgetBuilder(inflater: LayoutInflater, parent: HomeView, component: PrebookingComponent): PoiWidgetNodeHolder {
            return PoiWidgetNodeHolder(inflater, parent, component)
        }

        @PrebookingScope
        @Provides
        internal fun providePoiSelectorBuilder(inflater: LayoutInflater, parent: HomeView, component: PrebookingComponent): PoiSelectorNodeHolder {
            return PoiSelectorNodeHolder(inflater, parent, component)
        }

        @PrebookingScope
        @Provides
        internal fun provideBookingExtraBuilder(inflater: LayoutInflater, parent: HomeView, component: PrebookingComponent): BookingExtraNodeHolder {
            return BookingExtraNodeHolder(inflater, parent, component)
        }

        @PrebookingScope
        @Provides
        internal fun provideCarType(inflater: LayoutInflater, parent: HomeView, component: PrebookingComponent): CarTypeSelectorNodeHolder {
            return CarTypeSelectorNodeHolder(inflater, parent, component)
        }

        @Module
        interface Binders {

            @Binds
            fun provideSelectionListener(interactor: PrebookingInteractor): PoiSelectorInteractor.PoiSelectionListener

            @Binds
            fun providePoiClickListener(interactor: PrebookingInteractor): PoiWidgetInteractor.PoiClickListener

            @Binds
            fun provideCarTypeClickListener(interactor: PrebookingInteractor): CarTypeSelectorInteractor.Listener
        }
    }

    fun prebookingRepo(): RidePrebookingRepo

    fun selectionListener(): PoiSelectorInteractor.PoiSelectionListener

    fun clickListener(): PoiWidgetInteractor.PoiClickListener

    fun carTypeListener(): CarTypeSelectorInteractor.Listener

    fun bookingListener(): BookingExtraInteractor.Listener

    fun inflater(): LayoutInflater

    fun homeView(): HomeView
    
    fun getRouter(): PrebookingRouter
}
