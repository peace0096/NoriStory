package com.norispace.noristory.MainMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.norispace.noristory.LibraryActivity
import com.norispace.noristory.MyBooksActivity
import com.norispace.noristory.R
import com.norispace.noristory.Repository.User_Repo
import com.norispace.noristory.StoryMenuActivity
//import com.kakao.sdk.common.KakaoSdk.keyHash
//import com.kakao.sdk.common.util.Utility.getKeyHash
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("main", User_Repo.getToken())
        init()
//        val keyHash = getKeyHash(this /* context */);
//        Log.i("key", "$keyHash")
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
            intent.putExtra("btn", 0)
            startActivity(intent)
        }
        val myInfoBtn=findViewById<ImageView>(R.id.my_info_btn)
        myInfoBtn.setOnClickListener {
            val intent = Intent(this, MyInfoActivity::class.java)
            startActivity(intent)
        }
        val libraryBtn=findViewById<ImageView>(R.id.library_btn)
        libraryBtn.setOnClickListener {
            val intent = Intent(this, MyBooksActivity::class.java)
            intent.putExtra("btn", 1)
            startActivity(intent)
        }
    }
}