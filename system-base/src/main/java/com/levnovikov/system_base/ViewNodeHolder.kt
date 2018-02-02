package com.levnovikov.system_base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

abstract class ViewNodeHolder<out V : View, R : Router>(private val inflater: LayoutInflater, protected val parent: ViewGroup) : NodeHolder<R>() {

    private var view: V? = null

    abstract val layout: Int

    private fun destroyView() {
        if (view != null) {
            parent.removeView(view)
            view = null
        }
    }

    override fun destroy() {
        super.destroy()
        destroyView()
    }

    protected fun buildView(): V {
        if (view != null) {
            throw UnsupportedOperationException("View already attached")
        }
        Log.i(">>>>", "buildView " + this.javaClass.simpleName)
        val v = inflater.inflate(layout, parent, false) as V
        view = v
        return v
    }

    protected fun attachView() {
        Log.i(">>>>", "attachView " + this.javaClass.simpleName)
        parent.addView(view)
    }
}
