package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val storyBtn = findViewById<ImageView>(R.id.create_btn)
        storyBtn.setOnClickListener {
            val intent = Intent(this, StoryMenuActivity::class.java)
            startActivity(intent)
        }
        val bookshelfBtn=findViewById<ImageView>(R.id.bookshelf_btn)
        bookshelfBtn.setOnClickListener {
            val intent = Intent(this, MyBooksActivity::class.java)
            startActivity(intent)
        }
    }
}