package com.example.core_auth.provider.provider;

import io.reactivex.Single;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public interface AuthProvider {

    class Info {
        public final String token;
        public final String providerId;

        public Info(String token, String providerId) {
            this.token = token;
            this.providerId = providerId;
        }
    }

    Single<Info> login();
}
