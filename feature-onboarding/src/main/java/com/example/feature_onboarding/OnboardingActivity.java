package com.example.feature_onboarding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.feature_onboarding.di.ActivityModule;
import com.example.feature_onboarding.di.DaggerOnboardingComponent;
import com.example.feature_onboarding.di.OnboardingComponent;
import com.example.feature_onboarding.di.OnboardingModule;

import javax.inject.Inject;

public class OnboardingActivity extends AppCompatActivity {

    @Inject OnboardingView onboardingView;

    @Inject OnboardingInteractor interactor;

    private OnboardingComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(onboardingView);
        interactor.onGetActive();
    }

    private void injectDependencies() {
        component = DaggerOnboardingComponent.builder()
                .activityModule(new ActivityModule(this))
                .onboardingModule(new OnboardingModule())
                .build();
        component.inject(this);
    }
}
