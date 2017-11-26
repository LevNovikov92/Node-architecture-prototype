package com.levnovikov.core_profile;

import com.levnovikov.core_profile.model.UserProfile;

import io.reactivex.Single;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public interface UserRepository {

    Single<Boolean> isUserLoggedIn();

    Single<UserProfile> updateUserProfile();
}
