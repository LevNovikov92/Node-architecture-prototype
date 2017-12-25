package com.levnovikov.postbus.root.home.prebooking.poi_selector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.core_geo.Point;
import com.levnovikov.postbus.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

public class PoiSelectorView extends LinearLayout implements PoiSelectorInteractor.Presenter {

    public PoiSelectorView(Context context) {
        super(context);
    }

    public PoiSelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PoiSelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Inject
    PoiSelectorInteractor interactor;

    private BehaviorSubject<Point> selectedPoiStream = BehaviorSubject.create();
    private BehaviorSubject<String> placesStream = BehaviorSubject.create();
    private Adapter adapter;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        adapter = new Adapter((LayoutInflater) this.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE ));
        initView();
        interactor.onGetActive();
    }

    private void initView() {
        final EditText editText = findViewById(R.id.poi_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                placesStream.onNext(editable.toString());
            }
        });
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public Observable<Point> getSelectedPoi() {
        return selectedPoiStream;
    }

    @Override
    public Observable<String> getPlaceTitleStream() {
        return placesStream;
    }

    @Override
    public void updateSuggestions(List<Point> poiList) {
        adapter.setData(poiList);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
        }

        public void bind(Point point) {
            textView.setOnClickListener(v -> selectedPoiStream.onNext(point));
            textView.setText(point.title);
        }
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private LayoutInflater inflater;
        private List<Point> points = new ArrayList<>();

        Adapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void setData(List<Point> points) {
            this.points = points;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.poi_selector_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(points.get(position));
        }

        @Override
        public int getItemCount() {
            return points.size();
        }
    }
}
