package com.levnovikov.feature_promo.promo_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.levnovikov.feature_promo.R;
import com.levnovikov.feature_promo.domain.Promo;

import java.util.List;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

public class PromoListView extends LinearLayout implements PromoListPresenter {

    public PromoListView(Context context) {
        super(context);
    }

    public PromoListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PromoListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Adapter adapter;

    @Inject
    PromoListInteractor interactor;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
        interactor.onGetActive();
    }

    private void initView() {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter((LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setData(List<Promo> promoList) {
        if (adapter != null){
            adapter.setData(promoList);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.promo_title);
        }

        @SuppressLint("DefaultLocale")
        void bind(Promo promo) {
            title.setText(String.format("%s - %d", promo.title(), promo.priceOff()));
        }
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private final LayoutInflater inflater;

        Adapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        private List<Promo> data;

        void setData(List<Promo> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.promo_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
