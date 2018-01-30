package com.levnovikov.core_network;

import com.levnovikov.core_network.interceptors.AuthInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by lev.novikov
 * Date: 30/1/18.
 */

public class HttpClientBuilder {

    private AuthInterceptor authInterceptor;

    private final OkHttpClient.Builder builder = new OkHttpClient.Builder();

    public HttpClientBuilder(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    OkHttpClient build() {
        return builder.addInterceptor(authInterceptor).build();
    }
}
