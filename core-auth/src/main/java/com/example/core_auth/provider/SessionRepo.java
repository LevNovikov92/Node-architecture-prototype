package com.example.core_auth.provider;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.Nullable;

/**
 * Author: lev.novikov
 * Date: 29/1/18.
 */

@Singleton
public class SessionRepo {

    private final static String SESSION_TOKEN = "SESSION_TOKEN";
    private final SharedPreferences preferences;

    @Inject
    public SessionRepo(SharedPreferences preferences) {
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
    public void clearToken() {
        preferences.edit().remove(SESSION_TOKEN).commit();
    }
}
