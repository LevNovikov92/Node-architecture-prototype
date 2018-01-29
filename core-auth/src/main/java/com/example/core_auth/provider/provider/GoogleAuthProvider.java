package com.example.core_auth.provider.provider;

import io.reactivex.Single;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class GoogleAuthProvider implements AuthProvider {

    private final static String GOOGLE_PROVIDER_ID = "GOOGLE_PROVIDER_ID";

    @Override
    public Single<Info> login() {
        return Single.just(new Info("google_token", GOOGLE_PROVIDER_ID));
    }
}
