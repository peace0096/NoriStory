package com.norispace.noristory

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CharacterItemFragment : Fragment() {

    private var columnCount = 3
    private var data = arrayListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                initData()
                adapter = MyCharacterItemAdapter(data)
            }
        }
        return view
    }

    private fun initData(){
        data.add(R.drawable.signup_girl)
        data.add(R.drawable.signup_girl)
        data.add(R.drawable.signup_girl)
        data.add(R.drawable.signup_boy)
        data.add(R.drawable.signup_boy)
    }
}