package com.levnovikov.postbus.root.home.prebooking.poi_selector.di

import com.example.core_geo.Coordinates
import com.example.core_geo.Point
import com.example.core_location.PoiSuggestionProvider
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorInteractor
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorNodeHolder
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorRouter
import com.levnovikov.postbus.root.home.prebooking.poi_selector.PoiSelectorView
import com.levnovikov.system_base.base_di.ActivityComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

@PoiSelectorScope
@Component(dependencies = [(PrebookingComponent::class)], modules = [(PoiSelectorComponent.PoiSelectorModule::class)])
interface PoiSelectorComponent : ActivityComponent {

    fun inject(view: PoiSelectorView)

    fun router(): PoiSelectorRouter

    fun inject(poiSelectorBuilder: PoiSelectorNodeHolder)


    @Module
    class PoiSelectorModule(private val view: PoiSelectorView) {

        @PoiSelectorScope
        @Provides
        internal fun provideView(): PoiSelectorView {
            return view
        }

        @PoiSelectorScope
        @Provides
        internal fun providePresenter(): PoiSelectorInteractor.Presenter {
            return view
        }

        @PoiSelectorScope
        @Provides
        internal fun provideSuggestionProvider(): PoiSuggestionProvider {
            return object : PoiSuggestionProvider {

                private val subject = BehaviorSubject.createDefault<List<Point>>(ArrayList())

                private val suggestion = ArrayList<Point>()

                override fun getPoiStream(): Observable<List<Point>> {
                    return subject
                }

                override fun updatePlace(placeName: String) {
                    val coordinates = if (suggestion.size % 2 == 0)
                        Coordinates(-33.852, 151.211)
                    else
                        Coordinates(-32.852, 151.211)
                    suggestion.add(Point(
                            coordinates, String.format("Place %d", suggestion.size + 1)))
                    subject.onNext(suggestion)
                }
            }
        }

    }
}
