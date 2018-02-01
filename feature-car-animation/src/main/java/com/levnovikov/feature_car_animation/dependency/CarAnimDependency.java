package com.levnovikov.feature_car_animation.dependency;

import com.levnovikov.feature_map.dependency.MapProvider;
import com.levnovikov.system_base.base_di.ActivityComponent;

/**
 * Author: lev.novikov
 * Date: 27/1/18.
 */

public interface CarAnimDependency extends ActivityComponent {
    MapProvider mapProvider();
}
