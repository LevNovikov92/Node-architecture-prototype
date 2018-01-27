package com.levnovikov.postbus.root;

import com.levnovikov.core_profile.UserRepository;
import com.levnovikov.postbus.root.di.RootScope;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

@RootScope
public class RootInteractor {

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

    public void onGetActive() {
        checkUserData();
    }

    private void checkUserData() {
        userRepository.isUserLoggedIn()
                .subscribe(isLoggedIn -> {
                    if (isLoggedIn) {
                        loadProfile();
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
