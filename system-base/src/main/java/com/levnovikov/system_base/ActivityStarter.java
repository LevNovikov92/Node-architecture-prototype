package com.levnovikov.system_base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 22/11/17.
 */

public class ActivityStarter {

    public static final String EXTRA = "EXTRA";

    private final Context context;

    @Inject
    public ActivityStarter(Context context) {
        this.context = context;
    }

    public void startActivity(Class<? extends Activity> activity, @Nullable Bundle bundle) {
        final Intent intent = new Intent(context, activity);
        intent.putExtra(EXTRA, bundle);
        context.startActivity(intent);
    }
}
