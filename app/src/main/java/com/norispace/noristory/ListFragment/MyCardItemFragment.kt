package com.norispace.noristory.ListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.norispace.noristory.R

class MyCardItemFragment : Fragment() {

    private var columnCount = 1
    private var data = arrayListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mycard_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    else -> GridLayoutManager(context, columnCount)
                }
                initData()
//                val myAdapter =MyCardItemAdapter(data)
//                myAdapter.itemClickListener= object :
//                    MyCardItemAdapter.OnItemClickListener {
//                    override fun OnItemClick(
//                        holder: MyCardItemAdapter.ViewHolder,
//                        view: View,
//                        position: Int
//                    ) {
//                        //액티비티에 이미지 띄우기
//                    }
//
//                }
//                adapter = myAdapter
            }
        }
        return view
    }
    private fun initData(){
        data.add(R.drawable.signup_boy)
        data.add(R.drawable.signup_boy)
        data.add(R.drawable.signup_boy)
        data.add(R.drawable.signup_girl)
        data.add(R.drawable.signup_girl)
    }
}