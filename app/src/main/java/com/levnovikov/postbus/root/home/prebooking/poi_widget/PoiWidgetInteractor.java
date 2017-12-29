package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

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

    @Inject
    public PoiWidgetInteractor(
            PoiClickListener poiClickListener,
            Presenter presenter,
            PoiWidgetRouter router,
            ActivityState activityState,
            RidePrebookingRepo prebookingRepo) {
        super(router, activityState);
        this.poiSelectionListener = poiClickListener;
        this.presenter = presenter;

        prebookingRepo.pickupPoint.getStream()
                .subscribe(point -> presenter.setPickUp(point.title), e -> { /*handle*/ });

        prebookingRepo.dropOffPoint.getStream()
                .subscribe(point -> presenter.setDropOff(point.title), e -> { /*handle*/ });
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        presenter.onPickUpClick() //TODO unsubscribe
                .subscribe((v) -> {
                    poiSelectionListener.onPickUpSelected();
                }, (error) -> { /*handle it*/ });

        presenter.onDropOffClick() //TODO unsubscribe
                .subscribe((v) -> poiSelectionListener.onDropOffSelected(), (error) -> { /*handle it*/ });
    }

    public interface Presenter {
        Observable<Object> onPickUpClick();
        Observable<Object> onDropOffClick();

        void setPickUp(String placeTitle);
        void setDropOff(String placeTitle);
    }
}
