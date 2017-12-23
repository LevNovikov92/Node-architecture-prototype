package com.example.feature_onboarding.signup;

import com.example.feature_onboarding.signup.di.SignUpScope;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

@SignUpScope
public class SignUpRouter extends Router {

    @Inject
    SignUpRouter() {

    }

    @Override
    protected void detach() {

    }
}
