package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.system_base.Interactor;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

public class PoiWidgetInteractor implements Interactor {

    private final PoiWidgetRouter router;
    private final Presenter presenter;
    private final RidePrebookingRepo ridePrebookingRepo;

    PoiWidgetInteractor(
            PoiWidgetRouter router,
            Presenter presenter,
            RidePrebookingRepo ridePrebookingRepo) {
        this.router = router;
        this.presenter = presenter;
        this.ridePrebookingRepo = ridePrebookingRepo;
    }

    @Override
    public void onGetActive() {
        presenter.onPickUpClick()
                .subscribe((v) -> {

                }, (error) -> { /*handle it*/ });
    }

    interface Presenter {
        Observable<Void> onPickUpClick();
        Observable<Void> onDropOffClick();
    }
}
