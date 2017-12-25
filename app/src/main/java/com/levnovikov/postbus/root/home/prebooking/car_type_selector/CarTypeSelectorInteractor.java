package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
public class CarTypeSelectorInteractor implements Interactor {

    private Presenter presenter;
    private CarTypeSelectorRouter router;
    private Listener listener;
    private RidePrebookingRepo prebookingRepo;

    public interface Presenter {
        Observable<Object> clickStream();
    }

    public interface Listener {
        void onServiceSelected();
    }

    @Inject
    CarTypeSelectorInteractor(
            Presenter presenter,
            CarTypeSelectorRouter router,
            Listener listener,
            RidePrebookingRepo prebookingRepo) {
        this.presenter = presenter;
        this.router = router;
        this.listener = listener;
        this.prebookingRepo = prebookingRepo;
    }

    @Override
    public void onGetActive() {
        presenter.clickStream() //TODO unsubscribe
                .subscribe(o -> {
                    router.attachTypeList();
                    listener.onServiceSelected();
                }, e -> { /*handle error*/ });
    }
}
