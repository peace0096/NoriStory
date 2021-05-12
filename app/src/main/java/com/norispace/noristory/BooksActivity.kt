package com.norispace.noristory

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        initRecyclerView()
    }

    private fun initRecyclerView(){
        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this, GridLayoutManager.VERTICAL,false)
        adapter=BooksAdapater(data)
        adapter.itemClickListener=object : BooksAdapater.OnItemClickListener{
            override fun OnItemClick(
                holder: BooksAdapater.ViewHolder,
                view: View,
                list: BookData,
                position: Int
            ) {
                if(position==0){
                    val intent= Intent(this@BooksActivity, SunMoon1Activity::class.java)
                    startActivity(intent)
                }
            }
        }
        recyclerView.adapter=adapter
    }

    private fun initData(){
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