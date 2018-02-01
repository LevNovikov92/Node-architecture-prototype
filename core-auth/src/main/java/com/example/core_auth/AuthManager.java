package com.example.core_auth;

import com.example.core_auth.api.AuthApi;
import com.example.core_auth.exceptions.UserIsNotAuthorizedException;
import com.example.core_auth.provider.AuthProvider;
import com.example.core_auth.use_cases.RefreshTokenUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import okhttp3.Interceptor;

/**
 * Author: lev.novikov
 * Date: 29/1/18.
 */

@Singleton
public class AuthManager {

    private final AuthApi api;
    private final AuthRepo authRepo;
    private final RefreshTokenUseCase refreshToken;

    @Inject
    public AuthManager(AuthApi api, AuthRepo authRepo, RefreshTokenUseCase refreshToken) {
        this.api = api;
        this.authRepo = authRepo;
        this.refreshToken = refreshToken;
    }

    public boolean isUserAuthorized() {
        return authRepo.getAuthInfo() != null;
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

    public Completable refreshToken(Interceptor.Chain chain) {
        if (isUserAuthorized()) {
            return refreshToken.refreshToken(chain);
        } else {
            return Completable.error(new UserIsNotAuthorizedException());
        }
    }
}
