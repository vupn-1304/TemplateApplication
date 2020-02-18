package com.pv.demoapp.ui.city.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.BaseViewHolder
import com.base.OnItemClick
import com.pv.demoapp.R
import com.pv.demoapp.data.model.City
import com.utils.ext.inflateExt
import kotlinx.android.synthetic.main.item_city.view.*

class CityAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var listCity = mutableListOf<City>()

    var onItemClick: OnItemClick? = null

    fun setListCitys(list: List<City>) {
        listCity = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
            BaseViewHolder(parent.inflateExt(R.layout.item_city))

    override fun getItemCount(): Int = listCity.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = listCity[position]

        with(holder.itemView) {
            tvNameCity.text = item.name
            if (item.selected) {
                tvNameCity.setBackgroundResource(R.drawable.bg_selected)
            } else {
                tvNameCity.setBackgroundResource(R.drawable.bg_default)
            }
            setOnClickListener { onItemClick?.onItemClickListener(it, holder.adapterPosition) }
        }
    }
}