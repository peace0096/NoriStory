package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.norispace.noristory.ListFragment.SharedBookData
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.databinding.ActivityReadMyBookBinding
import kotlinx.android.synthetic.main.activity_read_my_book.*

class ReadMyBookActivity : AppCompatActivity() {
    lateinit var binding:ActivityReadMyBookBinding
    private var data = ArrayList<SharedBookData>()
    lateinit var title:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadMyBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        init()
    }

    fun init()
    {
        binding.apply {
            imgPager?.adapter = ViewPagerAdapter(data)
            read_help?.setOnClickListener{

            }
            read_back?.setOnClickListener{
                Log.d("btn", "back")
                val intent = Intent(this@ReadMyBookActivity, MyBooksActivity2::class.java)
                intent.putExtra("title", title)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            read_home?.setOnClickListener{
                Log.d("btn", "back")
                val intent = Intent(this@ReadMyBookActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }
    }

    fun initData()
    {
        title = intent.getStringExtra("title").toString()

        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"도서관","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
    }
}