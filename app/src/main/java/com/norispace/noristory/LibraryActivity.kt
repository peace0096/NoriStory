package com.norispace.noristory

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.ListFragment.BookItemFragment
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.ViewModel.StoryViewModel
import com.norispace.noristory.databinding.ActivityLibraryBinding
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.activity_my_books.*
import java.io.File

class LibraryActivity : AppCompatActivity() {
    lateinit var binding: ActivityLibraryBinding
    lateinit var title:String
    private lateinit var dbHelper: DBHelper
    private lateinit var storyViewModel: StoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storyViewModel = StoryViewModel()
        dbHelper = DBHelper(this)
        init()
    }

    fun init()
    {
        title = intent.getStringExtra("title").toString()

        binding.apply {
            lib_title.text = "< "+title+" >"
            //val list = dbHelper.getAllSubjectStoryThumbnail()
            var imgName = ""

//            for(i in list)
//            {
//                if(title == i.title) {
//                    imgName = i.coverImage
//                    break
//                }
//            }
            var image = File("data/data/com.norispace.noristory/cache/" + imgName)
            val bitmap = BitmapFactory.decodeFile(image.absolutePath)
            lib_thum.setImageBitmap(bitmap)


            lib_read?.setOnClickListener{
                val intent = Intent(this@LibraryActivity, ReadMyBookActivity::class.java)
                intent.putExtra("back", 2)
                intent.putExtra("title", title)
                startActivity(intent)
            }
            lib_store?.setOnClickListener{

            }
            lib_cancle?.setOnClickListener{
                val frag = BookItemFragment()
                val bundle = Bundle()
                bundle.putInt("btn", 1)
                frag.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, frag).commit()
            }
            mybookHome?.setOnClickListener{
                val intent = Intent(this@LibraryActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }
    }
}