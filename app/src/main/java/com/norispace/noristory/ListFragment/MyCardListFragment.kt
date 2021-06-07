package com.norispace.noristory.ListFragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.norispace.noristory.R
import com.norispace.noristory.databinding.FragmentBackgroundBinding
import com.norispace.noristory.databinding.FragmentMyCardListBinding
import java.io.File
import java.lang.Exception

class MyCardListFragment : Fragment() {
    var binding: FragmentMyCardListBinding?=null
    private var characterData = arrayListOf<Bitmap>()
    private var subjectData = arrayListOf<Bitmap>()
    private var basicCardSelected=arrayListOf<Int>() //등장인물 선택하기 에서 선택된 카드번호들
    private var myCardSelected=arrayListOf<Int>()
    var type=1  // 1 -> 인물, 2 -> 소재카드
    var selectedType =2 //1 -> 등장인물 선택하기에서 선택한 카드들 보이기
    var add=0
    interface  OnDataPass{
        //fun onSelectedCardPass(data:ArrayList<Int>)
        fun onSelectedCardPass(data:Bitmap?,add:Int)
    }

    lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser= context as OnDataPass
    }

//    fun passData(type:Int,number:Int,isBasicCharacter:Int){
//        val data= ArrayList<Int>()
//        data.add(type)
//        data.add(number)
//        data.add(isBasicCharacter)
//        dataPasser.onSelectedCardPass(data)
//    }

    fun passData(img:Bitmap?,add:Int){
        dataPasser.onSelectedCardPass(img,add)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        try {
            selectedType= arguments?.getInt("selectedType",0)!!
            if(selectedType==1){
                basicCardSelected=arguments?.getIntegerArrayList("basicCharacter")!!
                myCardSelected=arguments?.getIntegerArrayList("myCharacter")!!
            }
        }catch(e : Exception){
            Log.i("checkSelected","omg")
        }
        try {
            add= arguments?.getInt("add",0)!!
        }catch (e : Exception){
            Log.i("asd","asd")
        }

        binding=FragmentMyCardListBinding.inflate(layoutInflater,container,false)
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
                    img:Bitmap,
                    position:Int,
                    lastIndex: Int
                ) {
                    if(position==lastIndex){
                        passData(img,1)
                    }else{
                        passData(img,0)
                    }

                }
            }
            val subjectAdapter= MyCardItemAdapter(subjectData)
            subjectAdapter.itemClickListener=object  : MyCardItemAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: MyCardItemAdapter.ViewHolder,
                    view: View,
                    img:Bitmap,
                    position:Int,
                    lastIndex: Int
                ) {
                    if(position==lastIndex){
                        passData(img,1)
                    }else{
                        passData(img,0)
                    }
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
                passData(null,0)
            }
        }
    }

    private fun initCharacterData(){
        if(selectedType==1){
            for(i in 0 until basicCardSelected.size){
                //
                val imgName="character"+(basicCardSelected[i]+1).toString()
                val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
                val bitmap =BitmapFactory.decodeResource(context?.resources,id)
                characterData.add(bitmap)
            }
            for(i in 0 until myCardSelected.size){
                var storagePath = context?.cacheDir.toString()
                storagePath += "/Image/Card/Character"
                val fileName = "card" + myCardSelected[i].toString()+".png"
                val file = File(storagePath, fileName)
                if(file.exists()){
                    val dir=storagePath+"/"+fileName
                    val bmp= BitmapFactory.decodeFile(dir)
                    characterData.add(bmp)
                }
            }
//            val imgName="add_card"
//            val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
//            val bitmap =BitmapFactory.decodeResource(context?.resources,id)
//            characterData.add(bitmap)
        }
        else{
            val characterNum = 12
            for (i in 1..characterNum) {
                val imgName = "character" + i.toString()
                val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
                val bitmap =BitmapFactory.decodeResource(context?.resources,id)
                characterData.add(bitmap)
            }
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
        if(add==1){
            var storagePath = context?.cacheDir.toString()
            storagePath += "/Image/Card/Character"
            var count=0
            while(true){
                val fileName = "card" + count.toString()+".png"
                val file = File(storagePath, fileName)
                if(file.exists()){
                    count++
                }else{
                    count--
                    break
                }
            }
            val fileName = "card" + count.toString()+".png"
            val file = File(storagePath, fileName)
            if(file.exists()){
                val dir=storagePath+"/"+fileName
                val bmp= BitmapFactory.decodeFile(dir)
                characterData.add(bmp)
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