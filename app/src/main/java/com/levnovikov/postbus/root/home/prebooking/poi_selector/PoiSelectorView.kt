package com.levnovikov.postbus.root.home.prebooking.poi_selector

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.core_geo.Point
import com.levnovikov.postbus.R
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 20/12/17.
 */

class PoiSelectorView : LinearLayout, PoiSelectorInteractor.Presenter {

    @Inject
    lateinit var interactor: PoiSelectorInteractor

    private val selectedPoiStream = BehaviorSubject.create<Point>()
    private val placesStream = BehaviorSubject.create<String>()
    private lateinit var adapter: Adapter

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        adapter = Adapter(this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        initView()
        interactor.onGetActive()
    }

    private fun initView() {
        val editText = findViewById<EditText>(R.id.poi_text)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                placesStream.onNext(editable.toString())
            }
        })
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun selectedPoi(): Observable<Point> {
        return selectedPoiStream
    }

    override fun placeTitleStream(): Observable<String> {
        return placesStream
    }

    override fun updateSuggestions(poiList: List<Point>) {
        adapter.setData(poiList)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.title)

        fun bind(point: Point) {
            textView.setOnClickListener { v -> selectedPoiStream.onNext(point) }
            textView.text = point.title
        }
    }

    inner class Adapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<ViewHolder>() {
        private var points: List<Point> = ArrayList()

        fun setData(points: List<Point>) {
            this.points = points
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(inflater.inflate(R.layout.poi_selector_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(points[position])
        }

        override fun getItemCount(): Int {
            return points.size
        }
    }
}
