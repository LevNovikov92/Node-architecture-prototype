package com.levnovikov.postbus.root

import com.levnovikov.core_profile.UserRepository
import com.levnovikov.postbus.root.di.RootScope
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

@RootScope
class RootInteractor @Inject constructor(
        private val router: RootRouter,
        private val userRepository: UserRepository) {

    init {
        onGetActive()
    }

    fun onGetActive() {
        checkUserData()
    }

    private fun checkUserData() {
        userRepository.isUserLoggedIn
                .subscribe({ isLoggedIn ->
                    if (isLoggedIn) {
                        loadProfile()
                    }
                }, { this.handleInitError(it) })
    }

    private fun loadProfile() {
        userRepository.updateUserProfile()
                .subscribe({ router.home() }, { this.handleInitError(it) })
    }

    private fun handleInitError(error: Throwable) {
        // TODO handle error
    }
}
