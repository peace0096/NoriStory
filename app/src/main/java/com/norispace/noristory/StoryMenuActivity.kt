package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StoryMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_menu)
        init()
    }

    private fun init()
    {
        val OptinalBtn = findViewById<Button>(R.id.OptinalBtn)
        val SubjectBtn = findViewById<Button>(R.id.SubjectBtn)

        OptinalBtn.setOnClickListener {
            val intent = Intent(this, OptionalActivity::class.java)
            startActivity(intent)
        }

        SubjectBtn.setOnClickListener {
            val intent = Intent(this, SubjectActivity::class.java)
            startActivity(intent)
        }
    }
}