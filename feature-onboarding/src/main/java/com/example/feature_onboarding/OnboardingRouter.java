package com.example.feature_onboarding;

import com.example.feature_onboarding.di.OnboardingScope;
import com.example.feature_onboarding.login.LoginNodeHolder;
import com.example.feature_onboarding.signup.SignUpNodeHolder;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.node_state.NodeState;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

@OnboardingScope
public class OnboardingRouter extends Router {

    private final LoginNodeHolder loginBuilder;
    private final SignUpNodeHolder signUpBuilder;

    @Inject
    OnboardingRouter(LoginNodeHolder loginBuilder, SignUpNodeHolder signUpBuilder) {
        this.loginBuilder = loginBuilder;
        this.signUpBuilder = signUpBuilder;
    }

    void attachLogInScreen() {
        attachNode(loginBuilder);
    }

    void removeAll() {
        detachNode(loginBuilder);
        detachNode(signUpBuilder);
    }

    void attachSignUpScreen() {
        attachNode(signUpBuilder);
    }

    @Override
    protected void destroyNode() {
        removeAll();
    }

    @Override
    public NodeState getNodeState(NodeState nodeState) {
        return nodeState;
    }

    @Override
    public void setState(NodeState state) {
        //TODO remove stub
    }
}
