package com.example.core_auth.provider;

import io.reactivex.Completable;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class GoogleAuthProvider implements AuthProvider {

    @Override
    public Completable login() {
        return Completable.fromAction(() -> Thread.sleep(1000));
    }

    @Override
    public Completable signUp(String phoneNumber) {
        return Completable.fromAction(() -> Thread.sleep(1000));
    }
}
