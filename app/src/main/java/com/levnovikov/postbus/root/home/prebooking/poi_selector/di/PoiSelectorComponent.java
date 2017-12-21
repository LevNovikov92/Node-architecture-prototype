package com.levnovikov.postbus.root.home.prebooking.poi_selector.di;

import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorView;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@PoiSelectorScope
@Component(dependencies = PrebookingComponent.class, modules = PoiSelectorComponent.PoiSelectorModule.class)
public class PoiSelectorComponent {

    @Module
    public class PoiSelectorModule {
        private PoiSelectorView view;

        public PoiSelectorModule(PoiSelectorView view) {
            this.view = view;
        }

        @PoiSelectorScope
        @Provides
        PoiSelectorView provideView() {
            return view;
        }

    }
}
