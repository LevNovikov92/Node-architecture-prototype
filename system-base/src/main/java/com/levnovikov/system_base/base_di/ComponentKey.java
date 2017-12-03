package com.levnovikov.system_base.base_di;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by lev.novikov
 * Date: 2/12/17.
 */

@MapKey
@Target({ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
public @interface ComponentKey {
    Class<? extends ComponentBuilder> value();
}
