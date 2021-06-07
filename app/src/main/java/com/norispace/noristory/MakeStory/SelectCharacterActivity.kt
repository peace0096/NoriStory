package com.norispace.noristory.MakeStory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.norispace.noristory.ListFragment.CharacterItemFragment
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.MakeCoverActivity
import com.norispace.noristory.MakeStoryActivity
import com.norispace.noristory.R
import com.norispace.noristory.databinding.ActivitySelectCharacterBinding

class SelectCharacterActivity : AppCompatActivity(),CharacterItemFragment.OnDataPass {
    lateinit var binding:ActivitySelectCharacterBinding
    private val basicCharacterFragment=CharacterItemFragment()
    private val myCharacterFragment=CharacterItemFragment()
    var basicSelectedList=ArrayList<Int>()
    var mySelectedList=ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
        init()
    }

    private fun initFragment(){
        val bundle1=Bundle(1)
        bundle1.putInt("type",1) // 1 -> 기본 제공 카드
        basicCharacterFragment.arguments=bundle1
        val bundle2=Bundle(1)
        bundle2.putInt("type",2) // 2 -> 내 카드
        myCharacterFragment.arguments=bundle2
        val fragment =supportFragmentManager.beginTransaction()
        fragment.replace(R.id.character_card_list,basicCharacterFragment)
        fragment.commit()
    }

    private fun init() {
        binding.apply {

            drawBtn?.setOnClickListener {
                val intent= Intent(this@SelectCharacterActivity,
                    MakeCardActivity::class.java)
                startActivity(intent)
            }
            mybookBack?.setOnClickListener {
                val intent= Intent(this@SelectCharacterActivity,
                    MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            mybookHome?.setOnClickListener {
                val intent= Intent(this@SelectCharacterActivity,
                    SelectSubjectActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            nextBtn?.setOnClickListener {
                val i= Intent(this@SelectCharacterActivity, MakeCoverActivity::class.java)
//                i.putExtra("basicCharacter",basicSelectedList)
//                i.putExtra("myCharacter",mySelectedList)
//                //i.putIntegerArrayListExtra("basicCharacter",basicSelectedList)
//                Log.i("countCheck",basicSelectedList.lastIndex.toString())
                startActivity(i)
            }
            basicCard?.setOnClickListener {
                basicCard.setImageResource(R.drawable.basic_card_active)
                myCard?.setImageResource(R.drawable.my_card_inactive)
                val fragment =supportFragmentManager.beginTransaction()
                fragment.replace(R.id.character_card_list,basicCharacterFragment)
                fragment.commit()
            }
            myCard?.setOnClickListener {
                basicCard?.setImageResource(R.drawable.basic_card_inactive)
                myCard.setImageResource(R.drawable.my_card_active)
                val fragment =supportFragmentManager.beginTransaction()
                fragment.replace(R.id.character_card_list,myCharacterFragment)
                fragment.commit()
            }
        }


    }

    override fun onSelectedCharacterPass(data: ArrayList<Int>) {
//        if(data[0]==1){ // 기본 제공 캐릭터
//            if(data[2]==1){//선택됨
//                basicSelectedList.add(data[1])
//            }else{//선택취소
//                for(i in 0 until basicSelectedList.lastIndex){
//                    if(data[1]==basicSelectedList[i]){
//                        basicSelectedList.removeAt(i)
//                    }
//                }
//            }
//        }else{ //내 캐릭터들
//            if(data[2]==1){//선택됨
//                mySelectedList.add(data[1])
//            }else{//선택취소
//                for(i in 0 until mySelectedList.lastIndex){
//                    if(data[1]==mySelectedList[i]){
//                        mySelectedList.removeAt(i)
//                    }
//                }
//            }
//        }
    }
}