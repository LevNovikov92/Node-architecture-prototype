package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.prebooking.poi_widget.di.PoiWidgetScope;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

@PoiWidgetScope
public class PoiWidgetInteractor implements Interactor {

    public interface PoiClickListener {
        void onPickUpSelected();
        void onDropOffSelected();
    }

    private final PoiClickListener poiSelectionListener;
    private final Presenter presenter;
    private final RidePrebookingRepo ridePrebookingRepo;

    @Inject
    public PoiWidgetInteractor(
            PoiClickListener poiClickListener,
            Presenter presenter,
            RidePrebookingRepo ridePrebookingRepo) {
        this.poiSelectionListener = poiClickListener;
        this.presenter = presenter;
        this.ridePrebookingRepo = ridePrebookingRepo;
    }

    @Override
    public void onGetActive() {
        presenter.onPickUpClick() //TODO unsubscribe
                .subscribe((v) -> poiSelectionListener.onPickUpSelected(), (error) -> { /*handle it*/ });

        presenter.onDropOffClick() //TODO unsubscribe
                .subscribe((v) -> poiSelectionListener.onDropOffSelected(), (error) -> { /*handle it*/ });
    }

    public interface Presenter {
        Observable<Void> onPickUpClick();
        Observable<Void> onDropOffClick();
    }
}
