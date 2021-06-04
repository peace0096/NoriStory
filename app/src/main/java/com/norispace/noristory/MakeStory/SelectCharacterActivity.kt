package com.norispace.noristory.MakeStory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.databinding.ActivitySelectCharacterBinding

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
        }

    }
}