package com.levnovikov.core_profile;

import io.reactivex.Single;

/**
 * Author: lev.novikov
 * Date: 20/11/17.
 */

public interface UserRepository {

    Single<Boolean> isUserLogedIn();
}
