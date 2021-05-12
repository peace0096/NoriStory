package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.databinding.ActivitySunMoon1Binding

class SunMoon1Activity : AppCompatActivity() {
    lateinit var binding :ActivitySunMoon1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySunMoon1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        binding.apply {
            nextButton?.setOnClickListener {
                val intent= Intent(this@SunMoon1Activity,SunMoon2Activity::class.java)
                startActivity(intent)
            }
            exitButton?.setOnClickListener {
                val intent= Intent(this@SunMoon1Activity,BooksActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }
    }
}