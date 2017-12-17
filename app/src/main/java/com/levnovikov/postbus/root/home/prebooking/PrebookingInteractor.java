package com.levnovikov.postbus.root.home.prebooking;

import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 17/12/17.
 */

class PrebookingInteractor implements Interactor {

    private final PrebookingRouter router;

    @Inject
    PrebookingInteractor(PrebookingRouter router) {
        this.router = router;
        onGetActive();
    }

    @Override
    public void onGetActive() {
        //TODO make init, start some views
    }
}
