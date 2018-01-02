package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListInteractor;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.state.ActivityState;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@CarTypeSelectorScope
public class CarTypeSelectorInteractor extends Interactor<CarTypeSelectorRouter>
        implements CarTypeListInteractor.TypeListListener {

    private Presenter presenter;
    private Listener listener;
    private RidePrebookingRepo prebookingRepo;

    @Override
    public void onCancel() {
        router.detachTypeList();
    }

    public interface Presenter {
        Observable<Object> clickStream();
    }

    public interface Listener {
        void onServiceSelected();
    }

    @Inject
    CarTypeSelectorInteractor(
            Presenter presenter,
            Listener listener,
            RidePrebookingRepo prebookingRepo,
            CarTypeSelectorRouter router,
            ActivityState activityState) {
        super(router, activityState);
        this.presenter = presenter;
        this.listener = listener;
        this.prebookingRepo = prebookingRepo;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        presenter.clickStream() //TODO unsubscribe
                .subscribe(o -> {
                    router.attachTypeList();
                    listener.onServiceSelected();
                }, e -> { /*handle error*/ });
    }
}
