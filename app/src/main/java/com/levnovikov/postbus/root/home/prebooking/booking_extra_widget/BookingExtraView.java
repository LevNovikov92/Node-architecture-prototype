package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.levnovikov.postbus.R;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class BookingExtraView extends LinearLayout implements BookingExtraInteractor.Presenter {
    public BookingExtraView(Context context) {
        super(context);
    }

    public BookingExtraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BookingExtraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Inject BookingExtraInteractor interactor;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
        interactor.onGetActive();
    }

    private void initView() {
        findViewById(R.id.book_button).setOnClickListener(v -> clickStream.onNext(new Object()));
    }

    private BehaviorSubject<Object> clickStream = BehaviorSubject.create();
    @Override
    public Observable<Object> getClickStream() {
        return clickStream;
    }
}
