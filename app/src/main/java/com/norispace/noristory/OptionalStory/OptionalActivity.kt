package com.norispace.noristory.OptionalStory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.Books.BookData
import com.norispace.noristory.Books.BooksAdapater
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.R
import kotlin.collections.ArrayList

class OptionalActivity : AppCompatActivity() {

    private val dbHelper = DBHelper(this)
    lateinit var adapter: BooksAdapater
    lateinit var recyclerView : RecyclerView
    lateinit var data:ArrayList<BookData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optional)
        initData()
        initRecyclerView()

    }
    private fun initRecyclerView(){
        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager= GridLayoutManager(this, 3)
        adapter= BooksAdapater(data)
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
        data = dbHelper.findStoryThumbnail("선택형")
    }
}