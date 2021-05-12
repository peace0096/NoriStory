package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.databinding.ActivitySunMoon1Binding
import com.norispace.noristory.databinding.ActivitySunMoon2Binding

class SunMoon2Activity : AppCompatActivity() {
    lateinit var binding : ActivitySunMoon2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySunMoon2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.apply {
            binding.apply {
                postButton?.setOnClickListener {
                    val intent= Intent(this@SunMoon2Activity,SunMoon1Activity::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                nextButton?.setOnClickListener {
                    val intent= Intent(this@SunMoon2Activity,SunMoon3Activity::class.java)
                    startActivity(intent)
                }
                exitButton?.setOnClickListener {
                    val intent= Intent(this@SunMoon2Activity,BooksActivity::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
            }
        }
    }
}