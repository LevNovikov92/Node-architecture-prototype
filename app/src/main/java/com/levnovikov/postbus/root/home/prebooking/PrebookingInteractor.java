package com.levnovikov.postbus.root.home.prebooking;

import android.os.Parcelable;
import android.util.Log;

import com.example.core_geo.Point;
import com.levnovikov.feature_map.map_wrapper.MapWrapper;
import com.levnovikov.feature_ride.ride.RidePrebookingData;
import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.feature_map.dependency.MapProvider;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetInteractor;
import com.levnovikov.stream_state.PrebookingState;
import com.levnovikov.system_base.StateDataProvider;
import com.levnovikov.system_base.StateInteractor;
import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
public class PrebookingInteractor extends
        StateInteractor<PrebookingRouter> implements
        PoiSelectorInteractor.PoiSelectionListener,
        PoiWidgetInteractor.PoiClickListener,
        CarTypeSelectorInteractor.Listener,
        StateDataProvider {

    private final RidePrebookingRepo prebookingRepo;
    private final Lifecycle lifecycle;
    private final MapProvider mapProvider;
    private PrebookingState state = PrebookingState.INITIAL;

    @Inject
    PrebookingInteractor(PrebookingRouter router,
                         RidePrebookingRepo prebookingRepo,
                         ActivityState activityState,
                         Lifecycle lifecycle,
                         MapProvider mapProvider) {
        super(router, activityState);
        this.prebookingRepo = prebookingRepo;
        this.lifecycle = lifecycle;
        this.mapProvider = mapProvider;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        if (!hasSavedState()) {
            router.showPoiWidget();
        } else {
            restoreStateIfPossible();
        }
        bindMapAndPrebookingRepo();
    }

    private void bindMapAndPrebookingRepo() {
        lifecycle.subscribeUntilDestroy(
                prebookingRepo.pickupPoint.getStream()
                .subscribe(point -> mapProvider.getMap()
                        .map(MapWrapper::new)
                        .subscribe(map -> map.setPickUp(point), e -> {}), e -> {}));
        lifecycle.subscribeUntilDestroy(prebookingRepo.dropOffPoint.getStream()
                .subscribe(point -> mapProvider.getMap()
                        .map(MapWrapper::new)
                        .subscribe(map -> map.setDropOff(point), e -> {}), e -> {}));
    }

    private void restoreStateIfPossible() {
        final Parcelable stateDataParcelable = getNodeStateData();
        if (stateDataParcelable != null) {
            final RidePrebookingData stateData = (RidePrebookingData) stateDataParcelable;
            prebookingRepo.setData(stateData);
        }
    }

    @Override
    public void onPoiSelected(Point point) {
        if (state == PrebookingState.PICK_UP_SELECTION) {
            prebookingRepo.pickupPoint.set(point);
            router.hidePoiChoice();
        } else if (state == PrebookingState.DROP_OFF_SELECTION) {
            prebookingRepo.dropOffPoint.set(point);
            state = PrebookingState.SET_SERVICE_TYPE;
            router.startServiceType();
            router.startBookingExtra();
        }
    }

    @Override
    public void onPoiSelectionCanceled() {
        router.hidePoiChoice();
    }

    @Override
    public void onPickUpSelected() {
        state = PrebookingState.PICK_UP_SELECTION;
        router.startPoiChoice();
    }

    @Override
    public void onDropOffSelected() {
        state = PrebookingState.DROP_OFF_SELECTION;
        router.startPoiChoice();
    }

    @Override
    public void onServiceSelected() {
        Log.i(">>>>", "Service selected. Update Bottom Nav.");
    }

    /**
     * Router will save this data when activity will go to background
     */
    @Override
    public Parcelable getStateData() {
        return prebookingRepo.getData();
    }

}
