package com.example.core_auth;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.example.core_auth.provider.AuthProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.Nullable;

/**
 * Author: lev.novikov
 * Date: 29/1/18.
 */

@Singleton
public class AuthRepo {

    private final static String SESSION_TOKEN = "SESSION_TOKEN";
    private final SharedPreferences preferences;
    private AuthProvider.Info authInfo;

    @Inject
    public AuthRepo(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Nullable
    public String getSessionToken() {
        return preferences.getString(SESSION_TOKEN, null);
    }

    @SuppressLint("ApplySharedPref")
    public void setSessionToken(String sessionToken) {
        preferences.edit().putString(SESSION_TOKEN, sessionToken).commit();
    }

    @SuppressLint("ApplySharedPref")
    public void clearSessionToken() {
        preferences.edit().remove(SESSION_TOKEN).commit();
    }

    public void setAuthInfo(AuthProvider.Info authInfo) {
        this.authInfo = authInfo;
    }

    public AuthProvider.Info getAuthInfo() {
        return authInfo;
    }

    void clearData() {
        clearSessionToken();
        authInfo = null;
    }
}
