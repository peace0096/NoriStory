package com.norispace.noristory

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.norispace.service.StoryViewModel
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class OptionalActivity : AppCompatActivity() {
    lateinit var storyViewModel : StoryViewModel

    lateinit var adapter: BooksAdapater
    lateinit var recyclerView : RecyclerView
    var data:ArrayList<BookData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optional)
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
                val intent= Intent(this@OptionalActivity, SunMoonActivity::class.java)
                val thread =  Thread {
                    val title = data[position].title
                    runOnUiThread {
                        intent.putExtra("title", title)
                        startActivity(intent)
                    }
                }
                thread.start()
            }
        }
        recyclerView.adapter=adapter
    }

    private fun initData(){

        storyViewModel.isSuccessGet.observe(this, androidx.lifecycle.Observer {

            Log.d("done", "$it")
            if (it) {

                val thread = Thread {
                    val list = storyViewModel.storyTitleCoverList.value
                    if (list != null) {
                        for(e in list) {
                            val url = storyViewModel.S3Helper.getImage(e.coverimage.toString())

                            val connection = URL(url.toString()).openConnection()
                            connection.doInput = true
                            connection.connect()
                            val inputStream = connection.getInputStream()
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            data.add(BookData(e.title_kor.toString(), BitmapDrawable(bitmap), e.title.toString()))
                        }
                    }
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
                thread.start()
            }
        })

        val thread1 = Thread {
            storyViewModel.getStoryTitleCover("선택형")
            Log.d("done", "${storyViewModel.isSuccessGet.value}")
        }
        thread1.start()

        val scan = Scanner(resources.openRawResource(R.raw.original_story))
        while(scan.hasNextLine()){
            val imgName = scan.nextLine()
            val title=scan.nextLine()
            val id = resources.getIdentifier(imgName, "drawable", packageName)
            //val cover=resources.getDrawable(id,null)
            val cover= ContextCompat.getDrawable(this, id)

            data.add(BookData(title,cover!!,"sunmoon"))
        }
        scan.close()


    }
}