package com.norispace.noristory.ListFragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.norispace.noristory.Model.SubjectStoryThumbnail_Model
import com.norispace.noristory.R
import java.io.File
import java.io.FileOutputStream

class MyBookItemAdapter(
    private val values: ArrayList<SubjectStoryThumbnail_Model>
) : RecyclerView.Adapter<MyBookItemAdapter.ViewHolder>() {

    var checkAry =Array(values.size,{0})

    interface OnItemClickListener{
        fun OnHeartClick(holder: MyBookItemAdapter.ViewHolder, view: View, position: Int)
        fun OnBookClick(holder: MyBookItemAdapter.ViewHolder, view: View, position: Int)
    }

    interface OnSetting{
        fun onHeartSetting(holder: MyBookItemAdapter.ViewHolder):Boolean
    }

    var itemClickListener: MyBookItemAdapter.OnItemClickListener?=null
    var set: MyBookItemAdapter.OnSetting?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.image.
        val image = File(item.coverImage)
        val bitmap = BitmapFactory.decodeFile(image.absolutePath)

        if(image.exists())
        {
            holder.image.setImageBitmap(bitmap)
            holder.title.text = item.title
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.book_cover)
        val heart: ImageView = view.findViewById(R.id.heart)
        val title:TextView=view.findViewById(R.id.book_title)
        val info:TextView=view.findViewById(R.id.book_info)
        init
        {
            heart.setOnClickListener {
                itemClickListener?.OnHeartClick(this,it,absoluteAdapterPosition)
            }
            image.setOnClickListener{
                itemClickListener?.OnBookClick(this,it,absoluteAdapterPosition)
            }
            if(set?.onHeartSetting(this) == true)
                heart.visibility = View.INVISIBLE
            else
                heart.visibility = View.VISIBLE
        }
    }

}