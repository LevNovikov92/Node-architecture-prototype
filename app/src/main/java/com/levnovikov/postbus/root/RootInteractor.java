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
    }

    @Override
    public void onGetActive() {
        router.splash();
        initializeProfile();
    }

    private void initializeProfile() {
        userRepository.isUserLogedIn()
                .subscribe(isLoggedIn -> {
                    if (isLoggedIn) {
                        router.home();
                    } else {
                        router.onboarding();
                    }
                }, error -> { /*TODO handle it*/ });
    }
}
