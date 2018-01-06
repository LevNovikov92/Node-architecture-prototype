package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.feature_map.map_wrapper.MapInterface;
import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.prebooking.di.DaggerPrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.Builder;

import io.reactivex.annotations.Nullable;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

public class PrebookingBuilder extends Builder<PrebookingRouter> {

    private final HomeComponent component;

    @Nullable
    private MapInterface mapInterface;

    public PrebookingBuilder(HomeComponent component) {
        this.component = component;
    }

    @Override
    public PrebookingRouter build() {
        if (mapInterface == null) {
            throw new RuntimeException("mapInterface isn't ready");
        }
        final PrebookingComponent cmp = DaggerPrebookingComponent.builder()
                .homeComponent(component)
                .prebookingModule(new PrebookingComponent.PrebookingModule(mapInterface))
                .build();
        cmp.inject(this);
        cmp.interactor().onGetActive();
        return router;
    }

    public void setMapInterface(MapInterface mapInterface) {
        this.mapInterface = mapInterface;
    }
}
