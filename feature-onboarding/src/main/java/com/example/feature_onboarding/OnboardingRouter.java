package com.example.feature_onboarding;

import com.example.feature_onboarding.di.OnboardingScope;
import com.example.feature_onboarding.login.LoginBuilder;
import com.example.feature_onboarding.login.LoginRouter;
import com.example.feature_onboarding.signup.SignUpBuilder;
import com.example.feature_onboarding.signup.SignUpRouter;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
public class OnboardingRouter extends Router {

    private final LoginBuilder loginBuilder;
    private final SignUpBuilder signUpBuilder;

    @Inject
    OnboardingRouter(LoginBuilder loginBuilder, SignUpBuilder signUpBuilder) {
        this.loginBuilder = loginBuilder;
        this.signUpBuilder = signUpBuilder;
    }

    void attachLogInScreen() {
        final LoginRouter router = loginBuilder.build();
        attachRouter(router);
    }

    void removeAll() {
        loginBuilder.destroy();
        signUpBuilder.destroy();
        detachChildren();
    }

    void attachSignUpScreen() {
        final SignUpRouter router = signUpBuilder.build();
        attachRouter(router);
    }

    @Override
    protected void destroyNode() {
        removeAll();
    }

    @Override
    public NodeState getNodeState() { //TODO remove stub
        return NodeState.create(this.getClass(), null);
    }

    @Override
    public void setState(NodeState state) {
        //TODO remove stub
    }
}
