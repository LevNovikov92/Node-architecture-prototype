package com.levnovikov.system_base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.levnovikov.system_base.exceptions.ViewIsAlreadyAttachedException

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

abstract class BindingNodeHolder<R : Router, B : ViewDataBinding>(
        private val inflater: LayoutInflater,
        protected val parent: ViewGroup
) : NodeHolder<R>() {

    private var view: View? = null

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

    @Suppress("UNCHECKED_CAST")
    private fun buildView(): View {
        if (view != null) {
            throw ViewIsAlreadyAttachedException(this)
        }
        Log.i(">>>>", "buildView " + this.javaClass.simpleName)
        val v = inflater.inflate(layout, parent, false)
        view = v
        return v
    }

    private fun setupDataBinding(v: View, vm: Any, brId: Int) {
        val binding: B = DataBindingUtil.bind(v)
        binding.setVariable(brId, vm)
    }

    private fun attachView(vm: Any, brId: Int) {
        Log.i(">>>>", "attachView " + this.javaClass.simpleName)
        view?.let { setupDataBinding(it, vm, brId) }
        parent.addView(view)
    }

    protected fun buildAndAttachView(vm: Any, brId: Int) {
        buildView()
        attachView(vm, brId)
    }
}
