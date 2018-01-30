package com.levnovikov.core_network;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by lev.novikov
 * Date: 30/1/18.
 */

@Singleton
public class RetrofitBuilder {

    private Retrofit.Builder builder = new Retrofit.Builder();
    private OkHttpClient client;
    private String baseUrl;

    RetrofitBuilder(OkHttpClient client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    Retrofit build() {
        return builder.baseUrl(baseUrl).client(client).build();
    }
}
