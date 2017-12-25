package com.levnovikov.system_base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

public abstract class ViewBuilder<V extends View, R extends Router> implements Builder<R> {

    private V view;
    private final LayoutInflater inflater;
    protected final ViewGroup parent;

    @Inject
    public R router; //TODO try to make protected

    public ViewBuilder(LayoutInflater inflater, ViewGroup parent) {
        this.inflater = inflater;
        this.parent = parent;
    }

    protected void destroyView() {
        if (view != null) {
            parent.removeView(view);
            view = null;
        }
    }

    @Override
    public void destroy() {
        Log.i(">>>>", "destroy " + this.getClass().getSimpleName());
        //interactor.onDestroy(); //TODO is it possible to detach view without calling destroy()?
        if (router != null) {
            router.destroyNode();
            router = null;
            destroyView();
        }
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
