package com.levnovikov.postbus.root.home.prebooking.di;

import com.levnovikov.feature_ride.ride.RidePrebookingData;
import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.prebooking.PrebookingBuilder;
import com.levnovikov.postbus.root.home.prebooking.PrebookingRouter;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

@PrebookingScope
@Component(dependencies = HomeComponent.class, modules = PrebookingComponent.PrebookingModule.class)
public interface PrebookingComponent {

    void inject(PrebookingBuilder prebookingBuilder);

    @Module
    class PrebookingModule {

        @PrebookingScope
        @Provides
        RidePrebookingData provideDefaultPrebooingData() {
            return new RidePrebookingData();
        }

        @PrebookingScope
        @Provides
        RidePrebookingRepo providePrebookingRepo(RidePrebookingData defaultData) {
            return new RidePrebookingRepo(defaultData);
        }
    }


    PrebookingRouter getRouter();

    RidePrebookingRepo prebookingRepo();
}
