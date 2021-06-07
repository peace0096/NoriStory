package com.norispace.noristory

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.Model.SubjectStoryThumbnail_Model
import com.norispace.noristory.Repository.Story_Repo
import com.norispace.noristory.ViewModel.StoryViewModel
import com.norispace.noristory.databinding.ActivityMyBooks2Binding
import kotlinx.android.synthetic.main.activity_my_books2.*
import java.io.File

class MyBooksActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMyBooks2Binding
    private var columnCount = 3
    private var data = ArrayList<SubjectStoryThumbnail_Model>()
    private lateinit var dbHelper: DBHelper
    private lateinit var storyViewModel: StoryViewModel
    lateinit var thumbnailModel: SubjectStoryThumbnail_Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBooks2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {

        val title = intent.getStringExtra("title")
        storyViewModel = StoryViewModel()
        dbHelper = DBHelper(this)
        //val list1 = dbHelper.getAllSubjectStoryThumbnail()
        var imgPath = ""

//        for(i in list1)
//        {
//            if(title == i.title) {
//                imgPath = i.coverImage
//                thumbnailModel = i
//                break
//            }
//        }

        val image = File("data/data/com.norispace.noristory/cache/" + imgPath)
        val bitmap = BitmapFactory.decodeFile(image.absolutePath)
        BookImageView.setImageBitmap(bitmap)

        binding.apply {
            BookNameText?.text = title
            val list = Story_Repo.getSubjectStoryThumbnailListModel()
            for(e in list) {
                if(e.title == title) {
                    val image = File("data/data/com.norispace.noristory/cache/" + e.coverImage)
                    val bitmap = BitmapFactory.decodeFile(image.absolutePath)
                    thumbnailModel = SubjectStoryThumbnail_Model(e.title, e.coverImage)
                    BookImageView?.setImageBitmap(bitmap)
                }
            }

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
                intent.putExtra("back", 1)
                intent.putExtra("title", title)
                startActivity(intent)
            }
            RemoveBook?.setOnClickListener{
                var builder = AlertDialog.Builder(this@MyBooksActivity2)
                builder.setTitle(title)
                builder.setMessage("이 책을 삭제하시겠습니까?")
                    .setPositiveButton("삭제"){
                            _, _ ->
                        //dbHelper.deleteSubjectStoryThumbnail(thumbnailModel)
                        storyViewModel.deleteSubjectStoryThumbnail(thumbnailModel)
                        //val list2 = dbHelper.getAllSubjectStory()


                        val intent = Intent(this@MyBooksActivity2, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)

                    }
                    .setNegativeButton("취소"){
                            _, _ ->
                    }
                    .show()

                }

        }
    }
}

