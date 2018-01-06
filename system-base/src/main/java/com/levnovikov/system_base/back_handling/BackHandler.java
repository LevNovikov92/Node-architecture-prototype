package com.levnovikov.system_base.back_handling;

import com.levnovikov.system_base.Router;

/**
 * Created by lev.novikov
 * Date: 1/1/18.
 */

public interface BackHandler {
    boolean onBackPressed();

    boolean isLastInStack(Class<? extends Router> _class);

    void popLastInStack();

    void removeFromBackStack(Class<? extends Router> _class);
}
