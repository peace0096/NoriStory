package com.norispace.noristory

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.norispace.pojo.Story.StoryTitleCoverResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class BooksActivity : AppCompatActivity() {
    lateinit var adapter: BooksAdapater
    lateinit var recyclerView : RecyclerView
    var data:ArrayList<BookData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        initData()
        val handler = Handler()

        initRecyclerView()
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
                    startActivity(intent)
                }
            }
        }
        recyclerView.adapter=adapter
    }

    private fun initData(){


        val call = RetrofitClient.service
        call.getStoryTitleCover("선택형").enqueue(object : Callback<StoryTitleCoverResponse> {
            override fun onFailure(call: Call<StoryTitleCoverResponse>, t: Throwable) {
                Log.d("error","Failed : $t")
            }

            override fun onResponse(
                call: Call<StoryTitleCoverResponse>,
                response: Response<StoryTitleCoverResponse>
            ) {
                Log.d("succed", "Succeed : $response")
                val result = response.body()?.result

                if (result != null) {
                    for(i in result) {

                        val thread = Thread({
                            val connection = URL(i.coverimage).openConnection()
                            connection.doInput = true
                            connection.connect()
                            val inputStream = connection.getInputStream()
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            data.add(BookData(i.title.toString(), BitmapDrawable(bitmap)))
                            Log.d("succed", "Succeed : $data.size")
                            runOnUiThread {
                                adapter.notifyDataSetChanged()
                            }

                        })
                        thread.start()

                    }
                }

            }

        })

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