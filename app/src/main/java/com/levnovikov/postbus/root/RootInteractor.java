package com.levnovikov.postbus.root;

import com.levnovikov.core_profile.UserRepository;
import com.levnovikov.system_base.Interactor;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public class RootInteractor implements Interactor {

    private final UserRepository userRepository;
    private final RootRouter router;

    @Inject
    RootInteractor(
            RootRouter router,
            UserRepository userRepository) {
        this.userRepository = userRepository;
        this.router = router;
        onGetActive();
    }

    @Override
    public void onGetActive() {
        checkUserData();
    }

    private void checkUserData() {
        userRepository.isUserLoggedIn()
                .subscribe(isLoggedIn -> {
                    if (isLoggedIn) {
                        loadProfile();
                    } else {
                        router.onboarding();
                    }
                }, this::handleInitError);
    }

    private void loadProfile() {
        userRepository.updateUserProfile()
                .subscribe(userProfile -> router.home(),
                        this::handleInitError);
    }

    private void handleInitError(Throwable error) {
        // TODO handle error
    }
}
