package com.levnovikov.postbus.root.home.prebooking.poi_widget;

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

    @Inject
    public PoiWidgetInteractor(
            PoiClickListener poiClickListener,
            Presenter presenter) {
        this.poiSelectionListener = poiClickListener;
        this.presenter = presenter;
    }

    @Override
    public void onGetActive() {
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
    }
}
