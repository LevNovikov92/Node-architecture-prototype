package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import com.levnovikov.feature_ride.ride.RidePrebookingRepo;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.car_type_list.CarTypeListInteractor;
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.di.CarTypeSelectorScope;
import com.levnovikov.system_base.Interactor;
import com.levnovikov.system_base.lifecycle.Lifecycle;
import com.levnovikov.system_base.node_state.ActivityState;

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
    private final Lifecycle lifecycle;

    @Override
    public void onCancel() {
        getRouter().detachTypeList();
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
            ActivityState activityState,
            Lifecycle lifecycle) {
        super(router, activityState);
        this.presenter = presenter;
        this.listener = listener;
        this.prebookingRepo = prebookingRepo;
        this.lifecycle = lifecycle;
    }

    @Override
    public void onGetActive() {
        super.onGetActive();
        lifecycle.subscribeUntilDestroy(presenter.clickStream()
                .subscribe(o -> {
                    getRouter().attachTypeList();
                    listener.onServiceSelected();
                }, e -> { /*handle error*/ }));
    }
}
