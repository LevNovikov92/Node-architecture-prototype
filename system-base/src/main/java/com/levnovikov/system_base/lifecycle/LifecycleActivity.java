package com.levnovikov.system_base.lifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author: lev.novikov
 * Date: 7/1/18.
 */

public class LifecycleActivity extends AppCompatActivity implements Lifecycle {

    private final BehaviorSubject<LifecycleEvent> lifecycleStream = BehaviorSubject.create();
    private final Map<LifecycleEvent, CompositeDisposable> disposableMap = new HashMap<>();

    @Override
    public void subscribeUntil(LifecycleEvent event, Disposable disposable) {
        final CompositeDisposable compositeDisposable = disposableMap.get(event);
        if (compositeDisposable != null) {
            disposableMap.put(event, new CompositeDisposable());
        }

        disposableMap.get(event).add(disposable);
    }

    @Override
    public void subscribeUntilDestroy(Disposable disposable) {
        final CompositeDisposable compositeDisposable = disposableMap.get(LifecycleEvent.DESTROY);
        if (compositeDisposable == null) {
            disposableMap.put(LifecycleEvent.DESTROY, new CompositeDisposable());
        }

        disposableMap.get(LifecycleEvent.DESTROY).add(disposable);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        lifecycleStream.onNext(LifecycleEvent.CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleStream.onNext(LifecycleEvent.START);
        dispose(LifecycleEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleStream.onNext(LifecycleEvent.RESUME);
        dispose(LifecycleEvent.RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleStream.onNext(LifecycleEvent.PAUSE);
        dispose(LifecycleEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleStream.onNext(LifecycleEvent.STOP);
        dispose(LifecycleEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        lifecycleStream.onNext(LifecycleEvent.DESTROY);
        dispose(LifecycleEvent.DESTROY);
        super.onDestroy();
    }

    private void dispose(LifecycleEvent event) {
        final CompositeDisposable disposable = disposableMap.get(event);
        if (disposable != null) {
            disposable.dispose();
            disposableMap.remove(event);
        }
    }
}
