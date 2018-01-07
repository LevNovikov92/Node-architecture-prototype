package com.levnovikov.postbus.root.home.prebooking.poi_widget.di;

import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetNodeHolder;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetInteractor;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetRouter;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetView;
import com.levnovikov.system_base.base_di.ActivityComponent;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
@Component(dependencies = PrebookingComponent.class, modules = PoiWidgetComponent.PoiWidgetModule.class)
public interface PoiWidgetComponent extends ActivityComponent {

    void inject(PoiWidgetNodeHolder poiWidgetBuilder);

    PoiWidgetRouter router();

    void inject(PoiWidgetView view);

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
