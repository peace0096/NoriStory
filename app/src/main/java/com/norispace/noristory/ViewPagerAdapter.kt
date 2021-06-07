package com.norispace.noristory

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.ListFragment.SharedBookData
import com.norispace.noristory.Model.SubjectStory_Model
import kotlinx.android.synthetic.main.activity_my_books2.*
import java.io.File

class ViewPagerAdapter(private val list: ArrayList<SubjectStory_Model>)
    : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.PagerViewHolder {
        Log.d("title", "adapter init")
        return PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bookpage, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.PagerViewHolder, position: Int) {
        for(i in list)
        {
            if(position == i.page)
                holder.bind(i.image)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val imageView = itemView.findViewById<ImageView>(R.id.pageView)

        fun bind(imagePath: String) {

            val image = File("data/data/com.norispace.noristory/cache/" + imagePath)
            val bitmap = BitmapFactory.decodeFile(image.absolutePath)
            imageView.setImageBitmap(bitmap)
        }
    }


}
