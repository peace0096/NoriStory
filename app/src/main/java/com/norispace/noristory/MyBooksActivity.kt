package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.Books.BookData
import com.norispace.noristory.ListFragment.BookItemFragment
import com.norispace.noristory.databinding.ActivityMyBooksBinding
import kotlinx.android.synthetic.main.activity_my_books.*
import java.util.*
import kotlin.collections.ArrayList

class MyBooksActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyBooksBinding

    var data:ArrayList<BookData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init()
    {
        val btn = intent.getIntExtra("btn", -1)
        val frag = BookItemFragment()
        val bundle = Bundle()

        binding.apply{
            if(btn == 0)
            {
                TitleView?.text = "나만의 서재"
                bundle.putInt("btn", 0)

            }
            else if(btn == 1)
            {
                TitleView?.text = "우리의 도서관"
                bundle.putInt("btn", 1)
            }
            frag.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, frag).commit()

            mybook_back.setOnClickListener{
                val intent = Intent(this@MyBooksActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            mybook_home.setOnClickListener{
                val intent = Intent(this@MyBooksActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            mybook_help.setOnClickListener {
                popupImg?.visibility=View.VISIBLE
                totalView.setOnClickListener {
                    popupImg?.visibility=View.GONE
                }
            }
        }
    }

}