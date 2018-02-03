package com.levnovikov.core_profile.di.stubs

import com.levnovikov.core_profile.UserRepository
import com.levnovikov.core_profile.model.UserProfile

import io.reactivex.Single

/**
 * Created by lev.novikov
 * Date: 21/11/17.
 */

class UserRepoStub : UserRepository {
    override val isUserLoggedIn: Single<Boolean>
        get() = Single.just(true)

    override fun updateUserProfile(): Single<UserProfile> {
        return Single.just(UserProfile(1, "John", "Doe")).map({ profile ->
            Thread.sleep(2000)
            profile
        })
    }
}
