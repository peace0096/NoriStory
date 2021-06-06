package com.norispace.noristory

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.ListFragment.SharedBookData

class ViewPagerAdapter(private val list: ArrayList<SharedBookData>)
    : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.PagerViewHolder {
        return PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bookpage, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.PagerViewHolder, position: Int) {
        holder.bind(list[position].cover)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val imageView = itemView.findViewById<ImageView>(R.id.pageView)

        fun bind(image: Drawable) {
            imageView.setImageDrawable(image)
        }
    }


}
