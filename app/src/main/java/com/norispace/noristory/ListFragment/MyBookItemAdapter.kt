package com.norispace.noristory.ListFragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.Model.SubjectStoryThumbnail_Model
import com.norispace.noristory.R
import com.norispace.noristory.Repository.User_Repo
import com.norispace.service.S3Helper
import java.io.File
import java.io.FileOutputStream

class MyBookItemAdapter(
    private val values: ArrayList<SubjectStoryThumbnail_Model>
) : RecyclerView.Adapter<MyBookItemAdapter.ViewHolder>() {

    var checkAry =Array(values.size,{0})
    lateinit var s3helper:S3Helper

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
        s3helper = S3Helper(parent.context)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_book_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.image.

        val image = File("data/data/com.norispace.noristory/cache/" + item.coverImage)
        holder.title.text = item.title

        if(!image.exists())
        {
            Log.d("adap", item.coverImage)
            var path = ArrayList<String>()
            path.add(item.coverImage)
            s3helper.downloadImage(path)
            //경로 저장해줄것
        }
        val bitmap = BitmapFactory.decodeFile(image.absolutePath)
        holder.image.setImageBitmap(bitmap)
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