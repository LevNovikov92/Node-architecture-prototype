package com.levnovikov.postbus.root.home.prebooking.poi_selector.di;

import com.example.core_geo.Coordinates;
import com.example.core_geo.Point;
import com.example.core_location.PoiSuggestionProvider;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorBuilder;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorInteractor;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorRouter;
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorView;

import java.util.ArrayList;
import java.util.List;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@PoiSelectorScope
@Component(dependencies = PrebookingComponent.class, modules = PoiSelectorComponent.PoiSelectorModule.class)
public interface PoiSelectorComponent {

    void inject(PoiSelectorBuilder view);

    PoiSelectorRouter router();


    @Module
    class PoiSelectorModule {
        private PoiSelectorView view;

        public PoiSelectorModule(PoiSelectorView view) {
            this.view = view;
        }

        @PoiSelectorScope
        @Provides
        PoiSelectorView provideView() {
            return view;
        }

        @PoiSelectorScope
        @Provides
        PoiSelectorInteractor.Presenter providePresenter() {
            return view;
        }

        @PoiSelectorScope
        @Provides
        PoiSuggestionProvider provideSuggestionProvider() {
            return new PoiSuggestionProvider() {

                private final BehaviorSubject<List<Point>> subject = BehaviorSubject.createDefault(new ArrayList<>());

                private final List<Point> suggestion = new ArrayList<>();

                @Override
                public Observable<List<Point>> getPoiStream() {
                    return subject;
                }

                @Override
                public void updatePlace(String placeName) {
                    suggestion.add(new Point(
                            new Coordinates(0, 0), String.format("Place %d", suggestion.size() + 1)));
                    subject.onNext(suggestion);
                }
            };
        }

    }
}
