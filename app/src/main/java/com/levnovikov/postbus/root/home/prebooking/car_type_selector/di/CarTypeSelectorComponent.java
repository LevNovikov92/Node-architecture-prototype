package com.levnovikov.postbus.root.home.prebooking.car_type_selector.di;

import android.view.LayoutInflater;

import com.levnovikov.postbus.root.home.HomeView;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorRouter;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorView;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListBuilder;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
@Component(dependencies = PrebookingComponent.class, modules = CarTypeSelectorComponent.CarTypeModule.class)
public interface CarTypeSelectorComponent {

    void inject(CarTypeSelectorView view);

    CarTypeSelectorRouter router();

    void inject(CarTypeSelectorBuilder carTypeSelectorBuilder);

    @Module
    class CarTypeModule {

        private CarTypeSelectorView view;

        public CarTypeModule(CarTypeSelectorView view) {
            this.view = view;
        }

        @CarTypeSelectorScope
        @Provides
        CarTypeSelectorView provideView() {
            return view;
        }

        @CarTypeSelectorScope
        @Provides
        CarTypeSelectorInteractor.Presenter providePresenter() {
            return view;
        }

        @CarTypeSelectorScope
        @Provides
        CarTypeListBuilder provideListBuilder(
                LayoutInflater inflater,
                HomeView homeScreen,
                CarTypeSelectorComponent parentComponent) {
            return new CarTypeListBuilder(inflater, homeScreen, parentComponent);
        }
    }


}
