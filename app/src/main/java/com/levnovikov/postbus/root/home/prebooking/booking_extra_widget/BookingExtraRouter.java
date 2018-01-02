package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import android.os.Parcelable;

import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraScope;
import com.levnovikov.system_base.state.NodeState;
import com.levnovikov.system_base.Router;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

@BookingExtraScope
public class BookingExtraRouter extends Router {

    @Inject
    public BookingExtraRouter() {

    }

    @Override
    protected void destroyNode() {

    }

    @Override
    public NodeState getNodeState(@Nullable Parcelable stateData) {
        return NodeState.create(this.getClass(), null);
    }

    @Override
    public void setState(NodeState state) {

    }
}
