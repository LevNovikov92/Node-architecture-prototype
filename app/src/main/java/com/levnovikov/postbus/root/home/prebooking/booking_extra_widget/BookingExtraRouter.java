package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import android.os.Parcelable;

import com.levnovikov.feature_promo.promo_list.PromoListBuilder;
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

    private PromoListBuilder promoListBuilder;

    @Inject
    public BookingExtraRouter(PromoListBuilder promoListBuilder) {
        this.promoListBuilder = promoListBuilder;
    }

    @Override
    protected void destroyNode() {
        detachNode(promoListBuilder);
    }

    @Override
    public NodeState getNodeState(@Nullable Parcelable stateData) {
        final NodeState state = NodeState.create(this.getClass(), stateData);
        if (promoListBuilder.isActive()) {
            state.addNodeBuilder(promoListBuilder.getClass()); //TODO refactor it
        }
        return state;
    }

    @Override
    public void setState(NodeState state) {
        if (state.contains(PromoListBuilder.class)) {
            attachNode(promoListBuilder);
        }
    }

    void detachPromoList() {
        detachNode(promoListBuilder);
    }

    void attachPromoList() {
        attachNode(promoListBuilder);
    }
}
