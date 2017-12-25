package com.levnovikov.postbus.root.home.prebooking.booking_extra_widget;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.levnovikov.postbus.R;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.BookingExtraComponent;
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.di.DaggerBookingExtraComponent;
import com.levnovikov.postbus.root.home.prebooking.di.PrebookingComponent;
import com.levnovikov.system_base.ViewBuilder;

/**
 * Created by lev.novikov
 * Date: 23/12/17.
 */

public class BookingExtraBuilder extends ViewBuilder<BookingExtraView, BookingExtraRouter> {

    private PrebookingComponent parentComponent;

    public BookingExtraBuilder(LayoutInflater inflater, ViewGroup parent, PrebookingComponent parentComponent) {
        super(inflater, parent);
        this.parentComponent = parentComponent;
    }

    @Override
    public BookingExtraRouter build() {
        final BookingExtraView view = buildView();
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        view.setLayoutParams(params);

        final BookingExtraComponent component = DaggerBookingExtraComponent
                .builder()
                .prebookingComponent(parentComponent)
                .bookingExtraModule(new BookingExtraComponent.BookingExtraModule(view))
                .build();
        component.inject(view);
        component.inject(this);
        attachView();
        return router;
    }

    @Override
    public int getLayout() {
        return R.layout.booking_extra_widget;
    }
}
