package com.levnovikov.postbus.root.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.levnovikov.postbus.root.home.di.HomeComponent;
import com.levnovikov.postbus.root.home.di.HomeModule;
import com.levnovikov.system_base.base_di.SubComponentProvider;
import com.levnovikov.system_base.state.ActivityState;
import com.levnovikov.system_base.state.NodeState;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

public class HomeActivity extends AppCompatActivity {

    private static String HOME_ACTIVITY_STATE = "HOME_ACTIVITY_STATE";

    @Inject
    HomeView view;

    @Inject
    HomeInteractor interactor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityState activityState = null;
        if (savedInstanceState != null) {
            activityState = savedInstanceState.getParcelable(HOME_ACTIVITY_STATE);
        }
        injectDependencies(activityState);
        setContentView(view);
    }

    private void injectDependencies(@Nullable ActivityState activityState) { //TODO inject state in Activity scope
        final android.app.Application app = getApplication();
        if (app instanceof SubComponentProvider) {
            ((HomeComponent.Builder) ((SubComponentProvider) app).provide(HomeComponent.Builder.class))
                    .homeModule(new HomeModule(
                            this,
                            activityState != null ? activityState : ActivityState.create(new HashMap<>())))
                    .build()
                    .inject(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        final Map<String, NodeState> stateMap = interactor.getState();
        outState.putParcelable(HOME_ACTIVITY_STATE, ActivityState.create(stateMap));
    }
}
