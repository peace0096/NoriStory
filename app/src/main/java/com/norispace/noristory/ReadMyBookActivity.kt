package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.ListFragment.SharedBookData
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.Model.SubjectStory_Model
import com.norispace.noristory.Repository.Story_Repo
import com.norispace.noristory.ViewModel.StoryViewModel
import com.norispace.noristory.databinding.ActivityReadMyBookBinding
import com.norispace.service.S3Helper
import kotlinx.android.synthetic.main.activity_read_my_book.*
import java.io.File

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
        storyViewModel = StoryViewModel()
        dbHelper = DBHelper(this)
        initObserve()
        initData()
        init()
    }

    fun initObserve() {
        storyViewModel.subjectstorymodellistmodel.observe(this, Observer {
            if(it != null) {
                val s3helper = S3Helper(this)
                data = it
                Log.d("observe", data[0].title)
                //val list = dbHelper.getAllSubjectStory()
                binding.apply {
                    imgPager?.adapter = ViewPagerAdapter(data)
                }

                for(e in it) {
                    val image = File("data/data/com.norispace.noristory/cache/" + e.image)

                    if(!image.exists())
                    {
                        var path = ArrayList<String>()
                        path.add(e.image)
                        s3helper.downloadImage(path)
                        //경로 저장해줄것
                    }
                }

//                for(i in list)  {
//                    var flag = true
//                    for(e in data) {
//                        if(e.title == i.title) {
//                            flag = false
//                            break
//                        }
//                    }
//                    if(flag)
//                        data.add(i)
//                }

            }
        })


    }

    fun init()
    {
        binding.apply {


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
        Log.d("title" , title)
        back = intent.getIntExtra("back", -1)
        storyViewModel.getSubjectStory(title)



    }
}