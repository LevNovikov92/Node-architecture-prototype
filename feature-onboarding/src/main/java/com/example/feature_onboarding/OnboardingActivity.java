package com.example.feature_onboarding;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.feature_onboarding.di.ActivityModule;
import com.example.feature_onboarding.di.OnboardingComponent;
import com.levnovikov.system_base.base_di.SubComponentProvider;

import javax.inject.Inject;

public class OnboardingActivity extends AppCompatActivity {

    @Inject OnboardingView onboardingView;

    @Inject OnboardingInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(onboardingView);
        interactor.onGetActive();
    }

    private void injectDependencies() {
        final Application app = getApplication();
        if (app instanceof SubComponentProvider) {
            ((OnboardingComponent.Builder) ((SubComponentProvider) app).provide(OnboardingComponent.Builder.class))
                    .activityModule(new ActivityModule(this))
                    .build()
                    .inject(this);
        }
    }
}
