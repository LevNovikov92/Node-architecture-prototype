package com.levnovikov.postbus.root.home.prebooking.poi_selector;

import com.example.core_geo.Point;
import com.example.core_location.PoiSuggestionProvider;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.di.PoiSelectorScope;
import com.levnovikov.system_base.Interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@PoiSelectorScope
public class PoiSelectorInteractor implements Interactor {

    public interface PoiSelectionListener {
        void onPoiSelected(Point point);
    }

    private PoiSuggestionProvider poiProvider;
    private Presenter presenter;
    private PoiSelectionListener listener;

    @Inject
    public PoiSelectorInteractor(
            PoiSuggestionProvider poiProvider,
            Presenter presenter,
            PoiSelectionListener listener) {
        this.poiProvider = poiProvider;
        this.presenter = presenter;
        this.listener = listener;
    }

    @Override
    public void onGetActive() {
        presenter.getPlaceTitleStream() //TODO unsubscribe
                .subscribe((title) -> poiProvider.updatePlace(title),
                        (error) -> { /*handle error*/ });

        poiProvider.getPoiStream() //TODO unsubscribe
                .subscribe((poiList) -> presenter.updateSuggestions(poiList),
                        (error) -> { /*handle error*/ });

        presenter.getSelectedPoi()
                .subscribe(point -> listener.onPoiSelected(point), error -> {});
    }

    public interface Presenter {
        Observable<Point> getSelectedPoi();
        Observable<String> getPlaceTitleStream();
        void updateSuggestions(List<Point> poiList);
    }
}
