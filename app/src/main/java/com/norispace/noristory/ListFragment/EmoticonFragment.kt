package com.norispace.noristory.ListFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.R

class EmoticonFragment : Fragment() {
    lateinit var myAdapter: EmoticonAdapter
    private var data = arrayListOf<Int>()
    var showState=false
    interface  OnDataPass{
        fun onDataPass(data:Int)
    }

    lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser= context as OnDataPass
    }

    fun passData(data:Int){
        dataPasser.onDataPass(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_emoticon, container, false)
        val recyclerView=view.findViewById<RecyclerView>(R.id.emoticonRecyclerView)
        val emoticonList=view.findViewById<ConstraintLayout>(R.id.emoticonList)
        val showBtn=view.findViewById<ImageView>(R.id.showEmoticonBtn)
        val layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.layoutManager=layoutManager
        initData()
        val myAdapter= EmoticonAdapter(data)
        myAdapter.itemClickListener=object  : EmoticonAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: EmoticonAdapter.ViewHolder,
                view: View,
                position: Int
            ) {
                passData(position)
            }
        }
        showBtn.setOnClickListener {
            if(!showState){
                showBtn.setImageResource(R.drawable.cancle_btn_big)
                emoticonList.visibility=View.VISIBLE
                showState=true
            }else{
                showBtn.setImageResource(R.drawable.add_icon_btn)
                emoticonList.visibility=View.GONE
                showState=false
            }
        }
        recyclerView.adapter=myAdapter
        return view
    }

    private fun initData() {
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
        data.add(R.drawable.cancle_btn_big)
    }
}