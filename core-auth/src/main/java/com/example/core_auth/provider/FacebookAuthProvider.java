package com.example.core_auth.provider;

import io.reactivex.Single;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class FacebookAuthProvider implements AuthProvider {

    private final static String FB_PROVIDER_ID = "FB_PROVIDER_ID";

    @Override
    public Single<Info> login() {
        return Single.just(new Info("fb_token", FB_PROVIDER_ID));
    }
}
