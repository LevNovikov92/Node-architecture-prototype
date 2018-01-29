package com.levnovikov.feature_auth.di;

import com.levnovikov.feature_auth.AuthView;
import com.levnovikov.feature_auth.dependency.AuthDependency;
import com.levnovikov.system_base.base_di.ActivityComponent;

import dagger.Component;
import dagger.Module;

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
@Component(dependencies = AuthDependency.class, modules = {AuthComponent.AuthModule.class})
public interface AuthComponent extends ActivityComponent {

    void inject(AuthView view);

    @Module
    public class AuthModule {

        private AuthView view;

        public AuthModule(AuthView view) {
            this.view = view;
        }
    }
}
