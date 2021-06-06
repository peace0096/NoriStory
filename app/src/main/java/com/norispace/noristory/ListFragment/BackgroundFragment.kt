package com.norispace.noristory.ListFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.norispace.noristory.databinding.FragmentBackgroundBinding


class BackgroundFragment : Fragment() {
    var binding:FragmentBackgroundBinding?=null
    private var data = arrayListOf<Int>()
    var showState=false
    interface  OnDataPass{
        fun onBackgroundPass(data:Int)
    }

    lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser= context as OnDataPass
    }

    fun passData(data:Int){
        dataPasser.onBackgroundPass(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBackgroundBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val layoutManager= GridLayoutManager(activity, 3)
            backgroundRecyclerView?.layoutManager=layoutManager
            initData()
            val myAdapter= BackgroundItemAdapter(data)
            myAdapter.itemClickListener=object  : BackgroundItemAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: BackgroundItemAdapter.ViewHolder,
                    view: View,
                    position: Int
                ) {
                    passData(data[position])
                }
            }
            if(!showState){
                root?.visibility=View.VISIBLE
                showState=true
            }
//            showBackgroundBtn?.setOnClickListener {
//                if(!showState){ // 리스트가 보여지지 않을 경우 리스트 출력
//                    showBackgroundBtn.visibility=View.GONE
//                    root?.visibility=View.VISIBLE
//                    backgroundList?.visibility=View.VISIBLE
//                    showState=true
                    cancleBtn?.setOnClickListener{
                        //root?.visibility=View.GONE
                        showState=false
                        passData(-1)
                        //showBackgroundBtn.visibility=View.VISIBLE
//
//                        showState=false
//                    }
//                }
            }
            backgroundRecyclerView?.adapter=myAdapter
        }
    }

    private fun initData(){
        val backgroundNum=9
        for(i in 1..backgroundNum){
            val bgName="background"+i.toString()
            val id = resources.getIdentifier(bgName, "drawable", context?.packageName)
            data.add(id)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}