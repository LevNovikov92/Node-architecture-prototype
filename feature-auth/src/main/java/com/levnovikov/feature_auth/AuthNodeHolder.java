package com.levnovikov.feature_auth;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.feature_auth.dependency.AuthDependency;
import com.levnovikov.feature_auth.di.AuthComponent;
import com.levnovikov.feature_auth.di.DaggerAuthComponent;
import com.levnovikov.system_base.ViewNodeHolder;

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

public class AuthNodeHolder extends ViewNodeHolder<AuthView, AuthRouter> {

    private AuthDependency authDependency;

    public AuthNodeHolder(LayoutInflater inflater, ViewGroup parent, AuthDependency authDependency) {
        super(inflater, parent);
        this.authDependency = authDependency;
    }

    @Override
    public AuthRouter build() {
        final AuthView view = buildView();
        final AuthComponent component = DaggerAuthComponent.builder()
                .authDependency(authDependency)
                .authModule(new AuthComponent.AuthModule(view))
                .build();
        component.inject(view);
        attachView();
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.auth_view;
    }
}
