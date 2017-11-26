package com.example.feature_onboarding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.feature_onboarding.di.ActivityModule;
import com.example.feature_onboarding.di.DaggerOnboardnigComponent;
import com.example.feature_onboarding.di.OnboardingModule;
import com.example.feature_onboarding.di.OnboardnigComponent;

import javax.inject.Inject;

public class OnboardingActivity extends AppCompatActivity {

    @Inject OnboardingView onboardingView;

    @Inject OnboardingInteractor interactor;

    private OnboardnigComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(onboardingView);
    }

    private void injectDependencies() {
        component = DaggerOnboardnigComponent.builder()
                .activityModule(new ActivityModule(this))
                .onboardingModule(new OnboardingModule())
                .build();
        component.inject(this);
    }
}
