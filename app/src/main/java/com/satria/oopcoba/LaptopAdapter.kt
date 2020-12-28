package com.satria.oopcoba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satria.opp.Database.Laptop
import kotlinx.android.synthetic.main.adapter_laptop.view.*

class LaptopAdapter (private val AllLaptop: ArrayList<Laptop>, private val listener: OnAdapterListener) : RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder {
        return LaptopViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_laptop, parent, false)
        )
    }

    override fun getItemCount() = AllLaptop.size

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
        val laptop = AllLaptop[position]
        holder.view.text_merk.text = laptop.merk
        holder.view.text_merk.setOnClickListener {
            listener.onClick(laptop)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(laptop)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(laptop)
        }

    }

    class LaptopViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Laptop>) {
        AllLaptop.clear()
        AllLaptop.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(laptop: Laptop)
        fun onDelete(laptop: Laptop)
        fun onUpdate(laptop: Laptop)
    }
}