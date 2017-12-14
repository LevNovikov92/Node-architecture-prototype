package com.levnovikov.postbus.root.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Author: lev.novikov
 * Date: 14/12/17.
 */

public class HomeView extends FrameLayout {

    public HomeView(@NonNull Context context) {
        super(context);
    }

    public HomeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
