package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class BookingExtraView extends LinearLayout {
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
        interactor.onGetActive();
    }
}
