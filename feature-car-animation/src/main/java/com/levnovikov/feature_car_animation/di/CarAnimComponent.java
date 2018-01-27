package com.levnovikov.feature_car_animation.di;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;

import com.levnovikov.feature_car_animation.CarAnimInteractor;
import com.levnovikov.feature_car_animation.CarAnimRouter;
import com.levnovikov.feature_car_animation.R;
import com.levnovikov.feature_car_animation.dependency.CarAnimDependency;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

@CarAnimScope
@Component(dependencies = CarAnimDependency.class, modules = { CarAnimComponent.CarAnimModule.class })
public interface CarAnimComponent {

    CarAnimInteractor interactor();

    CarAnimRouter router();

    @Module
    class CarAnimModule {
        @Provides
        VectorDrawable providesCarDravable(Context context) {
            return (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_vehicle_taxi);
        }
    }
}
