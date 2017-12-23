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

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
public class PrebookingInteractor implements
        Interactor,
        PoiSelectorInteractor.PoiSelectionListener,
        PoiWidgetInteractor.PoiClickListener,
        CarTypeSelectorInteractor.Listener {

    private final PrebookingRouter router;
    private final RidePrebookingRepo prebookingRepo;
    private PrebookingState state = PrebookingState.INITIAL;

    @Inject
    PrebookingInteractor(PrebookingRouter router, RidePrebookingRepo prebookingRepo) {
        this.router = router;
        this.prebookingRepo = prebookingRepo;
    }

    @Override
    public void onGetActive() {
        //TODO make init, start some views
        router.showPoiWidget();
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
