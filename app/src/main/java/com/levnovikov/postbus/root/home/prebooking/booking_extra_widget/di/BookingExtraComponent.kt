package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di

import android.view.LayoutInflater
import com.levnovikov.feature_promo.promo_list.PromoListNodeHolder
import com.levnovikov.feature_promo.promo_list.dependency.OnPromoSelectedListener
import com.levnovikov.feature_promo.promo_list.dependency.PromoListDependency
import com.levnovikov.postbus.root.home.HomeView
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraInteractor
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraView
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.extra.ExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.di.OptionsDependencies
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.system_base.base_di.ActivityComponent
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
@Component(dependencies = [(PrebookingComponent::class)], modules = [(BookingExtraComponent.BookingExtraModule::class)])
interface BookingExtraComponent : ActivityComponent, PromoListDependency, OptionsDependencies {

    fun inject(bookingExtraBuilder: BookingExtraView)

    fun router(): BookingExtraRouter
    fun inflater(): LayoutInflater

    fun inject(bookingExtraBuilder: BookingExtraNodeHolder)

    @Module
    class BookingExtraModule(private val view: BookingExtraView) {

        @BookingExtraScope
        @Provides
        internal fun provideView(): BookingExtraView {
            return view
        }

        @BookingExtraScope
        @Provides
        internal fun providePresenter(): BookingExtraInteractor.Presenter {
            return view
        }

        @BookingExtraScope
        @Provides
        internal fun provideOnPromoSelectedListener(interactor: BookingExtraInteractor): OnPromoSelectedListener {
            return interactor
        }

        @BookingExtraScope
        @Provides
        internal fun providePromoListDependency(component: BookingExtraComponent): PromoListDependency {
            return component
        }

        @BookingExtraScope
        @Provides
        internal fun providePromoListBuilder(inflater: LayoutInflater, parent: HomeView, parentComponent: PromoListDependency): PromoListNodeHolder {
            return PromoListNodeHolder(inflater, parent, parentComponent)
        }

        @BookingExtraScope
        @Provides
        internal fun provideExtraBuilder(inflater: LayoutInflater, parent: HomeView, parentComponent: BookingExtraComponent): ExtraNodeHolder {
            return ExtraNodeHolder(inflater, parent, parentComponent)
        }

        @BookingExtraScope
        @Provides
        internal fun provideOptionsBuilder(inflater: LayoutInflater, parent: HomeView, parentComponent: BookingExtraComponent) =
                OptionsNodeHolder(inflater, parent, parentComponent)

    }
}
