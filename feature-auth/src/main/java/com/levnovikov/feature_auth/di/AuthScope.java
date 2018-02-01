package com.levnovikov.feature_auth.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface AuthScope {
}
