package com.example.feature_onboarding.login;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Toast;

import com.example.feature_onboarding.R;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by lev.novikov
 * Date: 23/11/17.
 */

public class LoginView extends ConstraintLayout implements LoginPresenter {

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Inject
    LoginInteractor interactor;

    private PublishSubject<Void> facebookClickSubject = PublishSubject.create();
    private PublishSubject<Void> googleClickSubject = PublishSubject.create();
    private PublishSubject<Void> signUpClickSubject = PublishSubject.create();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findViewById(R.id.facebook).setOnClickListener(v -> facebookClickSubject.onNext(null));
        findViewById(R.id.google).setOnClickListener(v -> googleClickSubject.onNext(null));
        findViewById(R.id.sign_up).setOnClickListener(v -> signUpClickSubject.onNext(null));
        interactor.onGetActive();
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
    public Observable<Void> getFacebookClickStream() {
        return facebookClickSubject;
    }

    @Override
    public Observable<Void> getGoogleClickStream() {
        return googleClickSubject;
    }

    @Override
    public Observable<Void> getSignUpClickStream() {
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
