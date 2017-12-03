package com.levnovikov.postbus.root.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lev.novikov
 * Date: 26/11/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface RootScope {
}