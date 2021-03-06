package com.norispace.noristory.Books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.R

class BooksAdapater(val items:ArrayList<BookData>) : RecyclerView.Adapter<BooksAdapater.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(holder: ViewHolder, view:View, list: BookData, position: Int)
    }

    var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView2)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        init {
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[absoluteAdapterPosition], absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookadapter_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].name
        holder.imageView.setImageDrawable((items[position].cover))

    }

}