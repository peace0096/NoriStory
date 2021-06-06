package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.ListFragment.SharedBookData
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.Model.SubjectStory_Model
import com.norispace.noristory.ViewModel.StoryViewModel
import com.norispace.noristory.databinding.ActivityReadMyBookBinding
import kotlinx.android.synthetic.main.activity_read_my_book.*

class ReadMyBookActivity : AppCompatActivity() {
    lateinit var binding:ActivityReadMyBookBinding
    private var data = ArrayList<SubjectStory_Model>()
    lateinit var title:String
    private lateinit var dbHelper: DBHelper
    private lateinit var storyViewModel: StoryViewModel
    var back = 0

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
                if(back == 1) {
                    val intent = Intent(this@ReadMyBookActivity, MyBooksActivity2::class.java)
                    intent.putExtra("title", title)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                else if(back == 2) {
                    val intent = Intent(this@ReadMyBookActivity, LibraryActivity::class.java)
                    intent.putExtra("title", title)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
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
        data.clear()
        title = intent.getStringExtra("title").toString()
        back = intent.getIntExtra("back", -1)
        storyViewModel = StoryViewModel()
        dbHelper = DBHelper(this)
        var list = dbHelper.getAllSubjectStory()

        for(i in list)
        {
            if(i.title == title)
                data.add(i)
        }

    }
}