package com.levnovikov.postbus.root.home.prebooking;

import android.util.Log;

import com.example.core_geo.Point;
import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingScope;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.PoiWidgetInteractor;
import com.levnovikov.stream_state.PrebookingState;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
public class PrebookingInteractor extends
        Interactor<PrebookingRouter> implements
        PoiSelectorInteractor.PoiSelectionListener,
        PoiWidgetInteractor.PoiClickListener,
        CarTypeSelectorInteractor.Listener {

    private final RidePrebookingRepo prebookingRepo;
    private PrebookingState state = PrebookingState.INITIAL;

    @Inject
    PrebookingInteractor(PrebookingRouter router,
                         RidePrebookingRepo prebookingRepo,
                         ActivityState activityState) {
        super(router, activityState);
        this.prebookingRepo = prebookingRepo;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        //TODO make init, start some views
        if (activityState.findNodeState(router.getClass()) == null) {
            router.showPoiWidget();
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
}
