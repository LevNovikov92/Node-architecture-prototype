package com.example.core_auth.provider;

import com.example.core_auth.provider.api.AuthApi;
import com.example.core_auth.provider.provider.AuthProvider;

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
    private final SessionRepo sessionRepo;

    @Inject
    public AuthManager(AuthApi api, SessionRepo sessionRepo) {
        this.api = api;
        this.sessionRepo = sessionRepo;
    }

    public boolean isUserAuthorized() {
        return sessionRepo.getSessionToken() != null;
    }

    public Completable login(AuthProvider.Info authInfo) {
        return api.loginToBackend(authInfo.token, authInfo.providerId);
    }

    public Completable logout() {
        return Completable.fromAction(sessionRepo::clearToken);
    }
}
