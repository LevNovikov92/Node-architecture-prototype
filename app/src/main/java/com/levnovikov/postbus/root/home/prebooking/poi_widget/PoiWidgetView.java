package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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

    private PublishSubject<Void> pickUpSubject = PublishSubject.create();
    private PublishSubject<Void> dropOffSubject = PublishSubject.create();

    @Override
    public Observable<Void> onPickUpClick() {
        return pickUpSubject;
    }

    @Override
    public Observable<Void> onDropOffClick() {
        return dropOffSubject;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    private void initView() {
        findViewById(R.id.pickUpButton).setOnClickListener((v) -> pickUpSubject.onNext(null));
        findViewById(R.id.dropOffButton).setOnClickListener((v) -> dropOffSubject.onNext(null));
    }
}
