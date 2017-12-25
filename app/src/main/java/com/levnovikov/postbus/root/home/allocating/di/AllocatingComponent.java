package com.levnovikov.postbus.root.home.allocating.di;

import com.levnovikov.postbus.root.home.allocating.AllocatingBuilder;
import com.levnovikov.postbus.root.home.allocating.AllocatingRouter;
import com.levnovikov.postbus.root.home.allocating.AllocatingView;
import com.levnovikov.postbus.root.home.di.HomeComponent;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@AllocatingScope
@Component(dependencies = HomeComponent.class, modules = AllocatingComponent.AllocatingModule.class)
public interface AllocatingComponent {

    void inject(AllocatingView view);

    AllocatingRouter router();

    void inject(AllocatingBuilder allocatingBuilder);

    @Module
    class AllocatingModule {

        private AllocatingView view;

        public AllocatingModule(AllocatingView view) {
            this.view = view;
        }

        @AllocatingScope
        @Provides
        AllocatingView provideView() {
            return view;
        }
    }
}
