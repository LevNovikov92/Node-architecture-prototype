package com.example.core_auth.di;

import com.example.core_auth.provider.AuthProvider;
import com.example.core_auth.provider.FacebookAuthProvider;
import com.example.core_auth.provider.GoogleAuthProvider;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 25/11/17.
 */

@Module
public class AuthProviderModule {

    public static final String GOOGLE_AUTH_PROVIDER = "GOOGLE_AUTH_PROVIDER";
    public static final String FACEBOOK_AUTH_PROVIDER = "FACEBOOK_AUTH_PROVIDER";

    @Provides
    @Named(GOOGLE_AUTH_PROVIDER)
    AuthProvider provideGoogleAuthProvider() {
        return new GoogleAuthProvider();
    }

    @Provides
    @Named(FACEBOOK_AUTH_PROVIDER)
    AuthProvider provideFacebookAuthProvider() {
        return new FacebookAuthProvider();
    }
}
