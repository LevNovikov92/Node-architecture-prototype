package com.levnovikov.postbus.root.home.allocating;

import com.levnovikov.postbus.root.home.allocating.di.AllocatingScope;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@AllocatingScope
public class AllocatingRouter extends Router {

    @Inject
    AllocatingRouter() {

    }

    @Override
    protected void detach() {

    }
}
