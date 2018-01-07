package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import com.levnovikov.feature_promo.promo_list.PromoListBuilder;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraScope;
import com.levnovikov.system_base.Router;
import com.levnovikov.system_base.state.NodeState;

import javax.inject.Inject;

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
    public NodeState getNodeState(NodeState nodeState) {
        if (promoListBuilder.isActive()) {
            nodeState.addNodeBuilder(promoListBuilder.getClass()); //TODO refactor it
        }
        return nodeState;
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
