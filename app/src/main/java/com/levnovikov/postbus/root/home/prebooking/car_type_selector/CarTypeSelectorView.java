package com.levnovikov.postbus.root.home.prebooking.car_type_selector;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.levnovikov.postbus.R;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class CarTypeSelectorView extends ConstraintLayout implements CarTypeSelectorInteractor.Presenter {
    public CarTypeSelectorView(Context context) {
        super(context);
    }

    public CarTypeSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarTypeSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Inject
    CarTypeSelectorInteractor interactor;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
        interactor.onGetActive();
    }

    private void initView() {
        findViewById(R.id.layout).setOnClickListener(v -> {
            clickStream.onNext(new Object());
        });
    }


    private BehaviorSubject<Object> clickStream = BehaviorSubject.create();
    @Override
    public Observable<Object> clickStream() {
        return clickStream;
    }
}
