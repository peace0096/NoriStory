package com.norispace.noristory.ListFragment

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.norispace.noristory.R


class MyCardItemAdapter(
    private val values: ArrayList<Bitmap>
) : RecyclerView.Adapter<MyCardItemAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder: MyCardItemAdapter.ViewHolder, view: View,img:Bitmap,position: Int,lastIndex:Int)
    }

    var itemClickListener: OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_mycard_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.image.setImageResource(item)
        holder.image.setImageBitmap(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.cardImage)
        init {
            view.setOnClickListener {
                itemClickListener?.OnItemClick(this,it,values[absoluteAdapterPosition],absoluteAdapterPosition,values.lastIndex)
            }
        }
    }
}