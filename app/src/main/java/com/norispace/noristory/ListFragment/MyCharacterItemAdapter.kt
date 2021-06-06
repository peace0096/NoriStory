package com.norispace.noristory.ListFragment

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.R

class MyCharacterItemAdapter(
    private val values: ArrayList<Bitmap>
) : RecyclerView.Adapter<MyCharacterItemAdapter.ViewHolder>() {

    var checkAry =Array(values.size,{0})

    interface OnItemClickListener{
        fun OnHeartClick(holder: ViewHolder, view: View, position: Int)
    }

    var itemClickListener: OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.image.setImageResource(item) 이미지 붙여주기
        holder.image.setImageBitmap(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.book_cover)
        val heart: ImageView = view.findViewById(R.id.heart)
        init {
            view.setOnClickListener {
                itemClickListener?.OnHeartClick(this,it,absoluteAdapterPosition)
            }
        }
    }
}