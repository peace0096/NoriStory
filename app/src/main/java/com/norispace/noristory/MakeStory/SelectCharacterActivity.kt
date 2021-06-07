package com.norispace.noristory.MakeStory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        binding.apply {
            val bundle1=Bundle(1)
            bundle1.putInt("type",1) // 1 -> 기본 제공 카드
            basicCharacterFragment.arguments=bundle1
            val bundle2=Bundle(1)
            bundle2.putInt("type",2) // 2 -> 내 카드
            myCharacterFragment.arguments=bundle2
            val fragment =supportFragmentManager.beginTransaction()
            fragment.replace(R.id.character_card_list,basicCharacterFragment)
            fragment.commit()
            val fragment1 =supportFragmentManager.beginTransaction()
            fragment1.replace(R.id.my_character_card_list,myCharacterFragment)
            fragment1.commit()
            myCharacterCardList?.visibility=View.GONE
        }

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
                i.putIntegerArrayListExtra("basicCharacter",basicSelectedList)
                  i.putIntegerArrayListExtra("myCharacter",mySelectedList)

                startActivity(i)
            }
            basicCard?.setOnClickListener {
                basicCard.setImageResource(R.drawable.basic_card_active)
                myCard?.setImageResource(R.drawable.my_card_inactive)
                myCharacterCardList?.visibility=View.GONE
                characterCardList?.visibility=View.VISIBLE

            }
            myCard?.setOnClickListener {
                basicCard?.setImageResource(R.drawable.basic_card_inactive)
                myCard.setImageResource(R.drawable.my_card_active)
                characterCardList?.visibility=View.GONE
                myCharacterCardList?.visibility=View.VISIBLE
            }
        }


    }

    override fun onSelectedCharacterPass(data: ArrayList<Int>) {
//        Log.i("getData","Asfgasf")
        if(data[0]==1){
            if(data[1]==1){
                basicSelectedList.add(data[2])
            }else{
                for(i in 0 until basicSelectedList.lastIndex){
                    if(data[2]==basicSelectedList[i]){
                        basicSelectedList.removeAt(i)
                        break
                    }
                }
            }
        }else{
            if(data[1]==1) {
                mySelectedList.add(data[2])
            }else{
                for(i in 0 until mySelectedList.lastIndex){
                    if(data[2]==mySelectedList[i]){
                        mySelectedList.removeAt(i)
                        break
                    }
                }
            }
        }
//        for(i in 0 until basicSelectedList.lastIndex+1){
//            Log.i("selected",basicSelectedList[i].toString())
//        }
    }
}