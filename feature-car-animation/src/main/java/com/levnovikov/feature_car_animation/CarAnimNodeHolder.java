package com.levnovikov.feature_car_animation;

import com.levnovikov.feature_car_animation.dependency.CarAnimDependency;
import com.levnovikov.feature_car_animation.di.CarAnimComponent;
import com.levnovikov.feature_car_animation.di.DaggerCarAnimComponent;
import com.levnovikov.system_base.NodeHolder;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

public class CarAnimNodeHolder extends NodeHolder<CarAnimRouter> {

    private final CarAnimDependency dependency;

    public CarAnimNodeHolder(CarAnimDependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public CarAnimRouter build() {
        final CarAnimComponent component = DaggerCarAnimComponent.builder()
                .carAnimDependency(dependency)
                .build();
        component.interactor().onGetActive();
        return component.router();
    }
}
