package com.norispace.noristory.ListFragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.norispace.noristory.Repository.User_Repo
import com.norispace.service.S3Helper
import java.io.File

class CharacterItemFragment : Fragment() {

    private var columnCount = 3
    private var drawableData = arrayListOf<Int>()
    private var bitmapData  = arrayListOf<Bitmap>()
    var type=0
    var init=false
    interface  OnDataPass{
        fun onSelectedCharacterPass(data:ArrayList<Int>)
    }

    lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser= context as OnDataPass
    }

    fun passData(type:Int,number:Int,isSelected:Int){
        val data= ArrayList<Int>()
        data.add(type)
        data.add(number)
        data.add(isSelected)
        dataPasser.onSelectedCharacterPass(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_item_list, container, false)
        type= arguments?.getInt("type",0)!!
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                initData()
                if(type==1) {
                    val myAdapter =
                        BasicCharacterItemAdapter(
                            drawableData
                        )
                    myAdapter.itemClickListener= object :
                        BasicCharacterItemAdapter.OnItemClickListener {
                        override fun OnHeartClick(
                            holder: BasicCharacterItemAdapter.ViewHolder,
                            view: View,
                            position: Int
                        ) {
                            if(myAdapter.checkAry[position]==0){
                                holder.heart.setImageResource(R.drawable.heart_filled)
                                myAdapter.checkAry[position]=1
                                passData(type,position,1)//1 -> 추가
                            }else{
                                holder.heart.setImageResource(R.drawable.heart_empty)
                                myAdapter.checkAry[position]=0
                                passData(type,position,0)//0 -> 취소
                            }
                        }

                    }
                    adapter = myAdapter
                }else{
                    val myAdapter =
                        MyCharacterItemAdapter(
                            bitmapData
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
                                passData(type,position,1)//1 -> 추가
                            }else{
                                holder.heart.setImageResource(R.drawable.heart_empty)
                                myAdapter.checkAry[position]=0
                                passData(type,position,0)//0 -> 취소
                            }
                        }

                    }
                    adapter = myAdapter
                }
            }
        }
        return view
    }



    private fun initData(){
        if(!init) {
            init=true
            if (type == 1) {
                val characterNum = 12
                for (i in 1..characterNum) {
                    val imgName = "character" + i.toString()
                    val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
                    drawableData.add(id)
                }
            } else{

                val list = ArrayList<String>()
                for(e in User_Repo.getCardModel()) {
                    var path = context?.cacheDir.toString() + e
                    val file = File(path)

                    if (!file.exists()) {
                        list.add(e)
                    }
                }
                if(list.size > 0) {
                    val s3Helper = S3Helper(context!!)
                    s3Helper.downloadImage(list)
                }
                var storagePath = context?.cacheDir.toString() + "/" + User_Repo.getToken()
                storagePath += "/Image/Card/Character"
                var count=0
                while(true){
                    val fileName = "card" + count.toString()+".png"
                    Log.d("card", storagePath + "/" +  fileName)
                    val file = File(storagePath, fileName)
                    if(file.exists()){
                        val dir=storagePath+"/"+fileName

                        val bmp=BitmapFactory.decodeFile(dir)
                        bitmapData.add(bmp)
                        count++
                    }else{
                        break
                    }
                }
            }
        }
    }
}