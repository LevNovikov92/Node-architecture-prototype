package com.levnovikov.feature_auth

import com.levnovikov.feature_auth.di.AuthScope
import com.levnovikov.system_base.Router
import com.levnovikov.system_base.node_state.NodeState

import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 29/1/18.
 */

@AuthScope
class AuthRouter @Inject
constructor() : Router() {

    override val holders: Set<NodeHolder<*>> = setOf()

}
