package com.example.core_auth.provider;

import io.reactivex.Completable;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class FacebookAuthProvider implements AuthProvider {

    @Override
    public Completable login() {
        return Completable.error(new Exception());
    }

    @Override
    public Completable signUp(String phoneNumber) {
        return Completable.error(new Exception());
    }
}
