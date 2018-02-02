package com.levnovikov.core_profile

import com.levnovikov.core_profile.model.UserProfile

import io.reactivex.Single

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

interface UserRepository {

    val isUserLoggedIn: Single<Boolean>

    fun updateUserProfile(): Single<UserProfile>
}
