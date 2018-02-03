package com.levnovikov.system_base.base_di

import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by lev.novikov
 * Date: 2/12/17.
 */

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ComponentKey(val value: KClass<out ComponentBuilder>)
