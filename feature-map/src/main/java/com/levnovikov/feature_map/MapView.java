package com.levnovikov.feature_map;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.levnovikov.feature_map.lifecycle.MapLifecycleEvent;
import com.levnovikov.feature_map.lifecycle.MapLifecycleListener;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public class MapView extends com.google.android.gms.maps.MapView implements MapLifecycleListener {

    public MapView(@NonNull Context context) {
        super(context);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Inject
    MapInteractor interactor;

    @Inject
    Observable<MapLifecycleEvent> mapLifecycleStream;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        interactor.onGetActive();
        initMap();
    }

    private void initMap() {
        getMapAsync(interactor);
        mapLifecycleStream //TODO unsubscribe
                .subscribe(event -> {
                    switch (event) {
                        case CREATE:
                            onCreate(event.getBundle());
                            break;
                        case RESUME:
                            onResume();
                            break;
                        case PAUSE:
                            onPause();
                            break;
                        case DESTROY:
                            onDestroy();
                            break;
                    }
                }, e -> { });
    }
}
