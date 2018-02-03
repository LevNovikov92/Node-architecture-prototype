package com.levnovikov.system_base.lifecycle

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

/**
 * Author: lev.novikov
 * Date: 7/1/18.
 */

open class LifecycleActivity : AppCompatActivity(), Lifecycle {

    private val lifecycleStream = BehaviorSubject.create<LifecycleEvent>()
    private val disposableMap = HashMap<LifecycleEvent, CompositeDisposable>()

    override fun subscribeUntil(event: LifecycleEvent, disposable: Disposable) {
        val compositeDisposable = disposableMap[event]
        if (compositeDisposable != null) {
            disposableMap[event] = CompositeDisposable()
        }

        disposableMap[event]?.add(disposable)
    }

    override fun subscribeUntilDestroy(disposable: Disposable) {
        val compositeDisposable = disposableMap[LifecycleEvent.DESTROY]
        if (compositeDisposable == null) {
            disposableMap[LifecycleEvent.DESTROY] = CompositeDisposable()
        }

        disposableMap[LifecycleEvent.DESTROY]?.add(disposable)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        lifecycleStream.onNext(LifecycleEvent.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycleStream.onNext(LifecycleEvent.START)
        dispose(LifecycleEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleStream.onNext(LifecycleEvent.RESUME)
        dispose(LifecycleEvent.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleStream.onNext(LifecycleEvent.PAUSE)
        dispose(LifecycleEvent.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleStream.onNext(LifecycleEvent.STOP)
        dispose(LifecycleEvent.STOP)
    }

    override fun onDestroy() {
        lifecycleStream.onNext(LifecycleEvent.DESTROY)
        dispose(LifecycleEvent.DESTROY)
        super.onDestroy()
    }

    private fun dispose(event: LifecycleEvent) {
        val disposable = disposableMap[event]
        if (disposable != null) {
            disposable.dispose()
            disposableMap.remove(event)
        }
    }
}
