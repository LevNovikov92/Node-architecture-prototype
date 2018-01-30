package com.example.core_auth.api;

import io.reactivex.Single;

/**
 * Author: lev.novikov
 * Date: 29/1/18.
 */

public interface AuthApi {

    //URL
    Single<String> loginToBackend(String token, String type);


}
