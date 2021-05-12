package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.databinding.ActivitySunMoon2Binding
import com.norispace.noristory.databinding.ActivitySunMoon3Binding

class SunMoon3Activity : AppCompatActivity() {
    lateinit var binding : ActivitySunMoon3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySunMoon3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.apply {
            binding.apply {
                postButton?.setOnClickListener {
                    val intent= Intent(this@SunMoon3Activity,SunMoon2Activity::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                nextButton?.setOnClickListener {
                    val intent= Intent(this@SunMoon3Activity,SunMoon4Activity::class.java)
                    startActivity(intent)
                }
                exitButton?.setOnClickListener {
                    val intent= Intent(this@SunMoon3Activity,BooksActivity::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
            }
        }
    }
}