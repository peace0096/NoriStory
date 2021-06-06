package com.norispace.noristory.ListFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.R

class BackgroundItemAdapter     (private val values: ArrayList<Int>
) : RecyclerView.Adapter<BackgroundItemAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder: ViewHolder, view: View, position: Int)
    }

    var itemClickListener: OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_background_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.background.setImageResource(item)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val background: ImageView = view.findViewById(R.id.backgroundItem)
        init {
            view.setOnClickListener {
                itemClickListener?.OnItemClick(this,it,absoluteAdapterPosition)
            }
        }
    }
}
