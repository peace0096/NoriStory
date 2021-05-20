package com.norispace.noristory

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.norispace.pojo.Story.StoryTitleCoverResponse
import com.norispace.service.RetrofitClient
import com.norispace.service.S3Helper
import com.norispace.service.StoryViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class BooksActivity : AppCompatActivity() {
    lateinit var storyViewModel :StoryViewModel

    lateinit var adapter: BooksAdapater
    lateinit var recyclerView : RecyclerView
    var data:ArrayList<BookData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        storyViewModel = StoryViewModel(applicationContext)
        initRecyclerView()
        initData()


    }

    private fun initRecyclerView(){
        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager= GridLayoutManager(this, 3)
        adapter=BooksAdapater(data)
        adapter.itemClickListener=object : BooksAdapater.OnItemClickListener{
            override fun OnItemClick(
                holder: BooksAdapater.ViewHolder,
                view: View,
                list: BookData,
                position: Int
            ) {
                if(position==0){
                    val intent= Intent(this@BooksActivity, SunMoonActivity::class.java)
                    intent.putExtra("title", data[position].name)
                    //TODO viewmodel.getOptionalStory 호출해야 함
                    startActivity(intent)
                }
            }
        }
        recyclerView.adapter=adapter
    }

    private fun initData(){


        val thread = Thread {
            storyViewModel.getStoryTitleCover("선택형")
            Thread.sleep(1000)

            if(storyViewModel.isSuccessGet.value == true) {
                val list = storyViewModel.storyTitleCoverList.value
                if (list != null) {
                    for(e in list) {
                        val url = storyViewModel.S3Helper.getImage(e.coverimage.toString())

                        val connection = URL(url.toString()).openConnection()
                        connection.doInput = true
                        connection.connect()
                        val inputStream = connection.getInputStream()
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        data.add(BookData(e.title.toString(), BitmapDrawable(bitmap)))
                    }
                }
                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
                storyViewModel.isSuccessGet.value == false

            }
            else {

            }
        }
        thread.start()

        val scan = Scanner(resources.openRawResource(R.raw.original_story))
        while(scan.hasNextLine()){
            val imgName = scan.nextLine()
            val title=scan.nextLine()
            val id = resources.getIdentifier(imgName, "drawable", packageName)
            //val cover=resources.getDrawable(id,null)
            val cover= ContextCompat.getDrawable(this, id)

            data.add(BookData(title,cover!!))
        }
        scan.close()


    }
}