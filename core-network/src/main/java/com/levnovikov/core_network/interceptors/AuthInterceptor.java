package com.levnovikov.core_network.interceptors;

import com.example.core_auth.AuthManager;
import com.levnovikov.core_network.ResponseCodes;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.Nullable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lev.novikov
 * Date: 30/1/18.
 */

@Singleton
public class AuthInterceptor implements Interceptor {

    @SuppressWarnings("FieldCanBeLocal")
    private static int MAX_REFRESH_TIME_SEC = 2;
    private final AuthManager authManager;

    private volatile boolean TOKEN_REFRESH_IN_PROGRESS = false;
    private volatile long LAST_TOKEN_UPDATE_TIME = 0;

    @Nullable
    private Throwable error = null;

    @Inject
    AuthInterceptor(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        return handleResponse(chain, request);
    }

    @SuppressWarnings("WeakerAccess")
    Response handleResponse(Chain chain, Request request) throws IOException {
        if (TOKEN_REFRESH_IN_PROGRESS) try {
            this.wait();
        } catch (InterruptedException e) { e.printStackTrace(); }

        final long requestStartTime = System.currentTimeMillis();
        final Response response = chain.proceed(request);
        switch (response.code()) {
            case ResponseCodes.NOT_AUTHORISED: {
                synchronized (this) {
                    if (requestStartTime > LAST_TOKEN_UPDATE_TIME) {
                        updateToken(chain);
                        if (error == null) {
                            return handleResponse(chain, request);
                        }
                    } else {
                        return handleResponse(chain, request);
                    }
                }
            }
        }
        return response;
    }

    private void updateToken(Chain chain) {
        TOKEN_REFRESH_IN_PROGRESS = true;
        authManager.refreshToken(chain)
                .timeout(MAX_REFRESH_TIME_SEC, TimeUnit.SECONDS)
                .subscribe(() -> {
                    TOKEN_REFRESH_IN_PROGRESS = false;
                    LAST_TOKEN_UPDATE_TIME = System.currentTimeMillis();
                    error = null;
                    this.notifyAll();
                }, e -> {
                    TOKEN_REFRESH_IN_PROGRESS = false;
                    error = e;
                    this.notifyAll();
                });
    }
}
