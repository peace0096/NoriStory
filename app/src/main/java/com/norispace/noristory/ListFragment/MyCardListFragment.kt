package com.norispace.noristory.ListFragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.norispace.noristory.R
import com.norispace.noristory.databinding.FragmentBackgroundBinding
import com.norispace.noristory.databinding.FragmentMyCardListBinding
import java.io.File

class MyCardListFragment : Fragment() {
    var binding: FragmentMyCardListBinding?=null
    private var characterData = arrayListOf<Bitmap>()
    private var subjectData = arrayListOf<Bitmap>()
    private var basicCardSelected=arrayListOf<Int>() //등장인물 선택하기 에서 선택된 카드번호들
    private var myCardSelected=arrayListOf<Int>()
    var type=1  // 1 -> 인물, 2 -> 소재카드
    var selectedType =1 //  1 -> 선택된 것들 있음, 2 -> 선택된 것들 없음

    interface  OnDataPass{
        fun onSelectedCardPass(data:ArrayList<Int>)
    }

    lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser= context as OnDataPass
    }

    fun passData(type:Int,number:Int){
        val data= ArrayList<Int>()
        data.add(type)
        data.add(number)
        dataPasser.onSelectedCardPass(data)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMyCardListBinding.inflate(layoutInflater,container,false)
        //selectedType=arguments?.getInt("")
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val layoutManager= LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            myCardRecyclerView?.layoutManager=layoutManager
            initCharacterData()
            initSubjectData()
            val characterAdapter= MyCardItemAdapter(characterData)
            characterAdapter.itemClickListener=object  : MyCardItemAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: MyCardItemAdapter.ViewHolder,
                    view: View,
                    position: Int
                ) {
                    passData(type,position)
                }
            }
            val subjectAdapter= MyCardItemAdapter(subjectData)
            subjectAdapter.itemClickListener=object  : MyCardItemAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: MyCardItemAdapter.ViewHolder,
                    view: View,
                    position: Int
                ) {
                    passData(type,position)
                }
            }
            showCharacterCardBtn?.setOnClickListener {
                showCharacterCardBtn.setImageResource(R.drawable.card_char_active)
                showSubjectCardBtn?.setImageResource(R.drawable.card_subject_inactive)
                myCardRecyclerView?.adapter=characterAdapter
                type=1
            }
            showSubjectCardBtn?.setOnClickListener {
                showCharacterCardBtn?.setImageResource(R.drawable.card_char_inactive)
                showSubjectCardBtn.setImageResource(R.drawable.card_subject_active)
                myCardRecyclerView?.adapter=subjectAdapter
                type=2
            }
            myCardRecyclerView?.adapter=characterAdapter
            cancleBtn?.setOnClickListener {
                passData(-1,-1)
            }
        }
    }

    private fun initCharacterData(){
        var storagePath = context?.cacheDir.toString()
        storagePath += "/Image/Card/Character"
        var count=0
        while(true){
            val fileName = "card" + count.toString()+".png"
            val file = File(storagePath, fileName)
            if(file.exists()){
                val dir=storagePath+"/"+fileName
                val bmp= BitmapFactory.decodeFile(dir)
                characterData.add(bmp)
                count++
            }else{
                break
            }
        }
    }

    private fun initSubjectData(){
        var storagePath = context?.cacheDir.toString()
        storagePath += "/Image/Card/Subject"
        var count=0
        while(true){
            val fileName = "card" + count.toString()+".png"
            val file = File(storagePath, fileName)
            if(file.exists()){
                val dir=storagePath+"/"+fileName
                val bmp= BitmapFactory.decodeFile(dir)
                subjectData.add(bmp)
                count++
            }else{
                break
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}