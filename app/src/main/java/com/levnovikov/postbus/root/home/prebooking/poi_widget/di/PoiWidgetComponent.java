package com.levnovikov.postbus.root.home.prebooking.poi_widget.di;

import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;

import dagger.Component;
import dagger.Module;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
@Component(dependencies = PrebookingComponent.class, modules = PoiWidgetComponent.PoiWidgetModule.class)
public interface PoiWidgetComponent {

    @Module
    class PoiWidgetModule {

    }
}
