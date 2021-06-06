package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.databinding.ActivitySelectCharacterBinding
import com.norispace.noristory.databinding.ActivitySetCharacterBinding

class SetCharacterActivity : AppCompatActivity() {
    lateinit var binding: ActivitySetCharacterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySetCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.apply {
            val i=intent
            val basicCharacterList=i.getIntArrayExtra("basicCharacter")
            val num= basicCharacterList?.get(0)?.plus(1)
            val imgName = "character" + num.toString()
            val id = resources.getIdentifier(imgName, "drawable", packageName)
            characterImg?.setImageResource(id)

            backBtn?.setOnClickListener {
                finish()
            }
            homeBtn?.setOnClickListener {
                val intent= Intent(this@SetCharacterActivity,
                    MainActivity::class.java)
                intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

        }
    }
}