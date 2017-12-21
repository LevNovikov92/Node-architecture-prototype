package com.levnovikov.postbus.root.home.prebooking.poi_widget.di;

import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetInteractor;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetRouter;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetView;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
@Component(dependencies = PrebookingComponent.class, modules = PoiWidgetComponent.PoiWidgetModule.class)
public interface PoiWidgetComponent {

    void inject(PoiWidgetBuilder poiWidgetBuilder);

    PoiWidgetRouter router();

    @Module
    class PoiWidgetModule {

        private PoiWidgetView view;

        public PoiWidgetModule(PoiWidgetView view) {
            this.view = view;
        }

        @PoiWidgetScope
        @Provides
        PoiWidgetView provideView() {
            return view;
        }

        @PoiWidgetScope
        @Provides
        PoiWidgetInteractor.Presenter providePresenter() {
            return view;
        }
    }

}
