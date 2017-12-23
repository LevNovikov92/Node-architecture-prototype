package com.levnovikov.system_base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

public abstract class ViewBuilder<V extends View, R extends Router> implements Builder<R> {

    protected V view;
    private final LayoutInflater inflater;
    protected final ViewGroup parent;

    public ViewBuilder(LayoutInflater inflater, ViewGroup parent) {
        this.inflater = inflater;
        this.parent = parent;
    }

    public void removeView() {
        if (view != null) {
            parent.removeView(view);
            view = null;
        }
    }

    public abstract int getLayout();

    protected V buildAndAttachView() {
        view = (V) inflater.inflate(getLayout(), parent, false);
        parent.addView(view);
        return view;
    }

    protected V buildView() {
        view = (V) inflater.inflate(getLayout(), parent, false);
        return view;
    }

    protected void attachView() {
        parent.addView(view);
    }
}
