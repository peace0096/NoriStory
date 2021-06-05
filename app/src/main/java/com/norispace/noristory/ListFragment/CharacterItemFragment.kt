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

class CharacterItemFragment : Fragment() {

    private var columnCount = 3
    private var data = arrayListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                initData()
                val myAdapter=
                    MyCharacterItemAdapter(
                        data
                    )
                myAdapter.itemClickListener= object :
                    MyCharacterItemAdapter.OnItemClickListener {
                    override fun OnHeartClick(
                        holder: MyCharacterItemAdapter.ViewHolder,
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
        data.add(R.drawable.signup_boy)
        data.add(R.drawable.signup_boy)
        data.add(R.drawable.signup_boy)
        data.add(R.drawable.signup_girl)
        data.add(R.drawable.signup_girl)
    }
}