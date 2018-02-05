package com.levnovikov.postbus.root.home.prebooking.car_type_selector.di

import android.view.LayoutInflater
import com.levnovikov.postbus.root.home.HomeView
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorInteractor
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorRouter
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorView
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListNodeHolder
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.TypeListListener
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.system_base.base_di.ActivityComponent
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
@Component(dependencies = [(PrebookingComponent::class)], modules = [(CarTypeSelectorComponent.CarTypeModule::class)])
interface CarTypeSelectorComponent : ActivityComponent {

    fun inject(view: CarTypeSelectorView)

    fun router(): CarTypeSelectorRouter

    fun typeListListener(): TypeListListener

    fun inject(carTypeSelectorBuilder: CarTypeSelectorNodeHolder)

    @Module
    class CarTypeModule(private val view: CarTypeSelectorView) {

        @CarTypeSelectorScope
        @Provides
        internal fun provideView(): CarTypeSelectorView {
            return view
        }

        @CarTypeSelectorScope
        @Provides
        internal fun providePresenter(): CarTypeSelectorInteractor.Presenter {
            return view
        }

        @CarTypeSelectorScope
        @Provides
        internal fun provideTypeListListener(interactor: CarTypeSelectorInteractor): TypeListListener {
            return interactor
        }

        @CarTypeSelectorScope
        @Provides
        internal fun provideListBuilder(
                inflater: LayoutInflater,
                homeScreen: HomeView,
                parentComponent: CarTypeSelectorComponent): CarTypeListNodeHolder {
            return CarTypeListNodeHolder(inflater, homeScreen, parentComponent)
        }
    }
}
