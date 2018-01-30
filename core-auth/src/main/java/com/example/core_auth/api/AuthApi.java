package com.example.core_auth.api;

import io.reactivex.Completable;

/**
 * Author: lev.novikov
 * Date: 29/1/18.
 */

public interface AuthApi {

    //URL
    Completable loginToBackend(String token, String type);
}
