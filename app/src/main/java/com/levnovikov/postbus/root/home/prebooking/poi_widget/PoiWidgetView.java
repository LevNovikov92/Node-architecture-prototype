package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.levnovikov.postbus.R;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

public class PoiWidgetView extends LinearLayout implements PoiWidgetInteractor.Presenter {

    public PoiWidgetView(Context context) {
        super(context);
    }

    public PoiWidgetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PoiWidgetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private PublishSubject<Object> pickUpSubject = PublishSubject.create();
    private PublishSubject<Object> dropOffSubject = PublishSubject.create();

    @Override
    public Observable<Object> onPickUpClick() {
        return pickUpSubject;
    }

    @Override
    public Observable<Object> onDropOffClick() {
        return dropOffSubject;
    }

    @Override
    public void setPickUp(String placeTitle) {
        ((Button) findViewById(R.id.pickUpButton)).setText(placeTitle);
    }

    @Override
    public void setDropOff(String placeTitle) {
        ((Button) findViewById(R.id.dropOffButton)).setText(placeTitle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    private void initView() {
        findViewById(R.id.pickUpButton).setOnClickListener((v) -> {
            Log.i(">>>", "click");
            pickUpSubject.onNext(new Object());
        });
        findViewById(R.id.dropOffButton).setOnClickListener((v) -> dropOffSubject.onNext(new Object()));
    }
}
