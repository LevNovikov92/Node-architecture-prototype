package com.levnovikov.postbus.root.home.allocating;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class AllocatingView extends LinearLayout {
    public AllocatingView(Context context) {
        super(context);
    }

    public AllocatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AllocatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
