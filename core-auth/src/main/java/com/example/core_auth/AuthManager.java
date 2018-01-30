package com.example.core_auth;

import com.example.core_auth.api.AuthApi;
import com.example.core_auth.provider.AuthProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

/**
 * Author: lev.novikov
 * Date: 29/1/18.
 */

@Singleton
public class AuthManager {

    private final AuthApi api;
    private final AuthRepo authRepo;

    @Inject
    public AuthManager(AuthApi api, AuthRepo authRepo) {
        this.api = api;
        this.authRepo = authRepo;
    }

    public boolean isUserAuthorized() {
        return authRepo.getSessionToken() != null;
    }

    public Completable login(AuthProvider.Info authInfo) {
        return api.loginToBackend(authInfo.token, authInfo.providerId)
                .flatMapCompletable(token -> {
                    authRepo.setSessionToken(token);
                    return Completable.complete();
                });
    }

    public Completable logout() {
        return Completable.fromAction(authRepo::clearData);
    }
}
