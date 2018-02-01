package com.levnovikov.feature_auth.view_model;

import com.levnovikov.feature_auth.di.AuthScope;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
public class AuthViewModelImpl implements AuthActions, AuthViewModel {

    @Inject
    AuthViewModelImpl() {

    }

    @Override
    public void onGetActive() {

    }
}
