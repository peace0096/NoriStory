package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MyInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)
        init()
    }
    private fun init(){
        val homeBtn=findViewById<ImageView>(R.id.home_btn)
        homeBtn.setOnClickListener {
            val i= Intent(this,MainActivity::class.java)
            i.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
        }
    }
}