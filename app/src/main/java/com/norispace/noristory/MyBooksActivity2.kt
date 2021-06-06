package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.databinding.ActivityMyBooks2Binding
import kotlinx.android.synthetic.main.activity_my_books2.*

class MyBooksActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMyBooks2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBooks2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {
        val title = intent.getStringExtra("title")

        binding.apply {
            BookNameText?.text = title
            back_Btn?.setOnClickListener {
                val intent = Intent(this@MyBooksActivity2, MyBooksActivity::class.java)
                intent.putExtra("btn", 0)
                startActivity(intent)
            }
            home_Btn?.setOnClickListener {
                val intent = Intent(this@MyBooksActivity2, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            help_Btn?.setOnClickListener {

            }
            ReadBook?.setOnClickListener{
                val intent = Intent(this@MyBooksActivity2, ReadMyBookActivity::class.java)
                intent.putExtra("title", title)
                startActivity(intent)
            }


        }
    }
}

