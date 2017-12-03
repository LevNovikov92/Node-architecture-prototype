package com.example.feature_onboarding.login;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Toast;

import com.example.feature_onboarding.R;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class LoginView extends ConstraintLayout implements LoginPresenter {

    static final int layout = R.layout.onboarding_login;

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private PublishSubject<Object> facebookClickSubject = PublishSubject.create();
    private PublishSubject<Object> googleClickSubject = PublishSubject.create();
    private PublishSubject<Object> signUpClickSubject = PublishSubject.create();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findViewById(R.id.facebook).setOnClickListener(v -> facebookClickSubject.onNext(new Object()));
        findViewById(R.id.google).setOnClickListener(v -> googleClickSubject.onNext(new Object()));
        findViewById(R.id.sign_up).setOnClickListener(v -> signUpClickSubject.onNext(new Object()));
    }

    @Override
    public void showProgress() {
        //TODO
    }

    @Override
    public void hideProgress() {
        //TODO
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public PublishSubject<Object> getFacebookClickStream() {
        return facebookClickSubject;
    }

    @Override
    public PublishSubject<Object> getGoogleClickStream() {
        return googleClickSubject;
    }

    @Override
    public PublishSubject<Object> getSignUpClickStream() {
        return signUpClickSubject;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        facebookClickSubject.onComplete();
        googleClickSubject.onComplete();
        signUpClickSubject.onComplete();
    }
}
