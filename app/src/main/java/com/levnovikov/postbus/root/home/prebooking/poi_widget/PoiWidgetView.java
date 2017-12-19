package com.levnovikov.postbus.root.home.prebooking.poi_widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Author: lev.novikov
 * Date: 19/12/17.
 */

public class PoiWidgetView extends LinearLayout {

    public PoiWidgetView(Context context) {
        super(context);
    }

    public PoiWidgetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PoiWidgetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }
}
