package com.norispace.noristory.ListFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.norispace.noristory.R


class BookItemFragment : Fragment() {

    private var columnCount = 3
    private var data = ArrayList<SharedBookData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                initData()
                val myAdapter=MyBookItemAdapter(data)
                myAdapter.itemClickListener= object :
                    MyBookItemAdapter.OnItemClickListener {
                    override fun OnHeartClick(
                        holder: MyBookItemAdapter.ViewHolder,
                        view: View,
                        position: Int
                    ) {
                        if(myAdapter.checkAry[position]==0){
                            holder.heart.setImageResource(R.drawable.heart_filled)
                            myAdapter.checkAry[position]=1
                        }else{
                            holder.heart.setImageResource(R.drawable.heart_empty)
                            myAdapter.checkAry[position]=0
                        }
                    }

                }

                adapter = myAdapter

            }
        }
        return view
    }
    private fun initData(){
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
    }
}