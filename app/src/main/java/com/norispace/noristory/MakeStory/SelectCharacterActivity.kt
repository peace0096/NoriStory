package com.norispace.noristory.MakeStory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.MainActivity
import com.norispace.noristory.SelectSubjectActivity
import com.norispace.noristory.databinding.ActivitySelectCharacterBinding
import kotlinx.android.synthetic.main.activity_make_card.*

class SelectCharacterActivity : AppCompatActivity() {
    lateinit var binding:ActivitySelectCharacterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        binding.apply {
            drawBtn?.setOnClickListener {
                val intent= Intent(this@SelectCharacterActivity,
                    MakeCardActivity::class.java)
                startActivity(intent)
            }
            homeBtn?.setOnClickListener {
                val intent= Intent(this@SelectCharacterActivity,
                    MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            backBtn?.setOnClickListener {
                val intent= Intent(this@SelectCharacterActivity,
                    SelectSubjectActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            basicCard?.setOnClickListener {

            }
            myCard?.setOnClickListener {

            }
        }


    }
}