package com.levnovikov.system_base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

class ActivityStarter @Inject constructor(
        @Named("AppContext") private val context: Context) { //TODO fix text constant

    fun startActivity(activity: Class<out Activity>, bundle: Bundle?) {
        val intent = Intent(context, activity)
        intent.putExtra(EXTRA, bundle)
        context.startActivity(intent)
    }

    companion object {
        private const val EXTRA = "EXTRA"
    }
}
