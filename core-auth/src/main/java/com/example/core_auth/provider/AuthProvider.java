package com.example.core_auth.provider;

import io.reactivex.Completable;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public interface AuthProvider {

    Completable login();
}
