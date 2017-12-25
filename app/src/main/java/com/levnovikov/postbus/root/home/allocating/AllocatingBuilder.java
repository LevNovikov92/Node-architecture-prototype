package com.levnovikov.postbus.root.home.allocating;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.allocating.di.AllocatingComponent;
import com.levnovikov.postbus.root.home.allocating.di.DaggerAllocatingComponent;
import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.system_base.ViewBuilder;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class AllocatingBuilder extends ViewBuilder<AllocatingView, AllocatingRouter> {

    private HomeComponent parentComponent;

    public AllocatingBuilder(LayoutInflater inflater, ViewGroup parent, HomeComponent parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public AllocatingRouter build() {
        final AllocatingView view = buildView();
        final AllocatingComponent component = DaggerAllocatingComponent.builder()
                .homeComponent(parentComponent)
                .allocatingModule(new AllocatingComponent.AllocatingModule(view))
                .build();
        component.inject(view);
        component.inject(this);
        attachView();
        return router;
    }

    @Override
    public int getLayout() {
        return R.layout.alloc_view;
    }
}
