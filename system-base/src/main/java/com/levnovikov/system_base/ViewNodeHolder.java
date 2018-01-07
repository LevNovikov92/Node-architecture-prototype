package com.levnovikov.system_base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

public abstract class ViewNodeHolder<V extends View, R extends Router> extends NodeHolder<R> {

    private V view;
    private final LayoutInflater inflater;
    protected final ViewGroup parent;

    public ViewNodeHolder(LayoutInflater inflater, ViewGroup parent) {
        this.inflater = inflater;
        this.parent = parent;
    }

    private void destroyView() {
        if (view != null) {
            parent.removeView(view);
            view = null;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        destroyView();
    }

    public abstract int getLayout();

    @SuppressWarnings("unchecked")
    protected V buildView() {
        if (view != null) {
            throw new UnsupportedOperationException("View already attached");
        }
        Log.i(">>>>", "buildView " + this.getClass().getSimpleName());
        view = (V) inflater.inflate(getLayout(), parent, false);
        return view;
    }

    protected void attachView() {
        Log.i(">>>>", "attachView " + this.getClass().getSimpleName());
        parent.addView(view);
    }
}
