package com.norispace.noristory.MainMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.LibraryActivity
import com.norispace.noristory.Model.SubjectStory_Model
import com.norispace.noristory.MyBooksActivity
import com.norispace.noristory.R
import com.norispace.noristory.Repository.Story_Repo
import com.norispace.noristory.Repository.User_Repo
import com.norispace.noristory.StoryMenuActivity
import com.norispace.noristory.ViewModel.StoryViewModel
import com.norispace.noristory.ViewModel.UserViewModel
import com.norispace.service.S3Helper
//import com.kakao.sdk.common.KakaoSdk.keyHash
//import com.kakao.sdk.common.util.Utility.getKeyHash
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var storyViewModel: StoryViewModel
    lateinit var userViewModel:UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("main", User_Repo.getToken())
        //initObserve()
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