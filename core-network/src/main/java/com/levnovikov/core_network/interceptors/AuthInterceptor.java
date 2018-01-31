package com.levnovikov.core_network.interceptors;

import com.example.core_auth.AuthManager;
import com.levnovikov.core_network.ResponseCodes;

import java.io.IOException;

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

    private static int RETRY_LIMIT = 3;
    private static int MAX_REFRESH_TIME_MILLIS = 3000;
    private final AuthManager authManager;

    volatile boolean TOKEN_REFRESH_IN_PROGRESS = false;
    volatile long LAST_TOKEN_UPDATE_TIME = 0;

    @Nullable
    private Throwable error = null;

    private int retryCounter = 0;

    @Inject
    public AuthInterceptor(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        Response response = handleResponse(chain, request);
        retryCounter = 0;
        return response;
    }

    Response handleResponse(Chain chain, Request request) throws IOException {
        if (TOKEN_REFRESH_IN_PROGRESS) waitForRefreshOrTimeout();

        final long requestStartTime = System.currentTimeMillis();
        final Response response = chain.proceed(request);
        response.body();
        System.out.println(Thread.currentThread().getName() + ": " + String.valueOf(System.currentTimeMillis()));
        switch (response.code()) {
            case ResponseCodes.NOT_AUTHORISED: {
                synchronized (this) {
                    if (requestStartTime > LAST_TOKEN_UPDATE_TIME) {
                        TOKEN_REFRESH_IN_PROGRESS = true;
                        authManager.refreshToken(chain)
                                .subscribe(() -> {
                                    TOKEN_REFRESH_IN_PROGRESS = false;
                                    LAST_TOKEN_UPDATE_TIME = System.currentTimeMillis();
                                    error = null;
                                }, e -> {
                                    TOKEN_REFRESH_IN_PROGRESS = false;
                                    error = e;
                                });
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

    void waitForRefreshOrTimeout() {
        int refreshTime = 0;
        while (TOKEN_REFRESH_IN_PROGRESS) {
            try {
                if (refreshTime > MAX_REFRESH_TIME_MILLIS) return;
                Thread.sleep(100);
                refreshTime += 100;
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
