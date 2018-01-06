package com.levnovikov.feature_map.di;

import com.levnovikov.feature_map.MapRouter;
import com.levnovikov.feature_map.MapView;
import com.levnovikov.feature_map.dependency.MapDependency;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

@MapScope
@Component(dependencies = MapDependency.class, modules = MapComponent.MapModule.class)
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
