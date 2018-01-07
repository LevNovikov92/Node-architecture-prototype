package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
public class PoiWidgetInteractor extends Interactor<PoiWidgetRouter> {

    public interface PoiClickListener {
        void onPickUpSelected();
        void onDropOffSelected();
    }

    private final PoiClickListener poiSelectionListener;
    private final Presenter presenter;
    private final Lifecycle lifecycle;

    @Inject
    public PoiWidgetInteractor(
            PoiClickListener poiClickListener,
            Presenter presenter,
            PoiWidgetRouter router,
            ActivityState activityState,
            Lifecycle lifecycle,
            RidePrebookingRepo prebookingRepo) {
        super(router, activityState);
        this.poiSelectionListener = poiClickListener;
        this.presenter = presenter;
        this.lifecycle = lifecycle;

        lifecycle.subscribeUntilDestroy(prebookingRepo.pickupPoint.getStream()
                .subscribe(point -> presenter.setPickUp(point.title()), e -> { /*handle*/ }));

        lifecycle.subscribeUntilDestroy(prebookingRepo.dropOffPoint.getStream()
                .subscribe(point -> presenter.setDropOff(point.title()), e -> { /*handle*/ }));
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        lifecycle.subscribeUntilDestroy(presenter.onPickUpClick()
                .subscribe((v) -> poiSelectionListener.onPickUpSelected(), (error) -> { /*handle it*/ }));

        lifecycle.subscribeUntilDestroy(presenter.onDropOffClick()
                .subscribe((v) -> poiSelectionListener.onDropOffSelected(), (error) -> { /*handle it*/ }));
    }

    public interface Presenter {
        Observable<Object> onPickUpClick();
        Observable<Object> onDropOffClick();

        void setPickUp(String placeTitle);
        void setDropOff(String placeTitle);
    }
}
