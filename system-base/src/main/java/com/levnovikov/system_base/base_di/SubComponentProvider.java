package com.levnovikov.system_base.base_di;

/**
 * Created by lev.novikov
 * Date: 2/12/17.
 */

public interface SubComponentProvider {
    <C extends ComponentBuilder> ComponentBuilder provide(Class<C> key);
}
