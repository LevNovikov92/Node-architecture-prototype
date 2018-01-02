package com.levnovikov.postbus.root.home.map.di;

import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.map.MapRouter;
import com.levnovikov.postbus.root.home.map.MapView;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
@Component(dependencies = HomeComponent.class, modules = MapComponent.MapModule.class)
public interface MapComponent {

    MapRouter router();

    void inject(MapView view);

    @Module
    class MapModule {

        private final MapView view;

        public MapModule(MapView view) {
            this.view = view;
        }

        @MapScope
        @Provides
        MapView provideView() {
            return view;
        }
    }
}
