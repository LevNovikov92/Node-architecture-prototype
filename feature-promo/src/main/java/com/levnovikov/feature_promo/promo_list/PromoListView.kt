package com.levnovikov.feature_promo.promo_list

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.levnovikov.feature_promo.R
import com.levnovikov.feature_promo.domain.Promo

import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 4/1/18.
 */

class PromoListView : LinearLayout, PromoListPresenter {

    private var adapter: Adapter? = null

    @Inject
    lateinit var interactor: PromoListInteractor

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initView()
        interactor.restoreState()
    }

    private fun initView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        recyclerView.adapter = adapter
    }

    override fun setData(promoList: List<Promo>) {
        adapter!!.setData(promoList)
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.promo_title)

        @SuppressLint("DefaultLocale")
        fun bind(promo: Promo) {
            title.text = String.format("%s - %d", promo.title, promo.priceOff)
        }
    }

    internal inner class Adapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<ViewHolder>() {

        private var data: List<Promo>? = null

        fun setData(data: List<Promo>) {
            this.data = data
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(inflater.inflate(R.layout.promo_list_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(data!![position])
        }

        override fun getItemCount(): Int {
            return data!!.size
        }
    }
}
