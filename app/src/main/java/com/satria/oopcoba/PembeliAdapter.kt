package com.satria.oopcoba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pembeli.view.*

class PembeliAdapter(val pembeli : ArrayList<Pembeli>, val onClick : OnClick) : RecyclerView.Adapter<PembeliAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pembeli, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = pembeli.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(pembeli.get(position))
        holder.itemView.btDeletePembeli.setOnClickListener {
            onClick.delete(pembeli.get(position).key)
        }
        holder.itemView.setOnClickListener {
            onClick.edit(pembeli.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pembeli: Pembeli) {
            itemView.tvPembeliName.text = pembeli.nama
            itemView.tvPembeliDescription.text = pembeli.alamat
        }
    }

    interface OnClick {
        fun delete(key: String?)
        fun edit(pembeli: Pembeli?)
    }

}