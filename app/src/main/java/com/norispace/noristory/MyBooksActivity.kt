package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.norispace.noristory.MainMenu.MainActivity
import androidx.recyclerview.widget.RecyclerView
import com.norispace.noristory.Books.BookData
import com.norispace.noristory.Books.BooksAdapater
import kotlinx.android.synthetic.main.activity_my_books.*
import java.util.*
import kotlin.collections.ArrayList

class MyBooksActivity : AppCompatActivity() {
    lateinit var adapter: BooksAdapater
    lateinit var recyclerView : RecyclerView
    var data:ArrayList<BookData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_books)
        initData()
        initRecyclerView()
    }
    private fun initRecyclerView(){
//        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.layoutManager= GridLayoutManager(this, 3)
//        adapter= BooksAdapater(data)
//        adapter.itemClickListener=object : BooksAdapater.OnItemClickListener{
//            override fun OnItemClick(
//                holder: BooksAdapater.ViewHolder,
//                view: View,
//                list: BookData,
//                position: Int
//            ) {
//                if(position==0){
//                    val intent= Intent(this@MyBooksActivity, BookMenuActivity::class.java)
//                    startActivity(intent)
//                }
//            }
//        }
//        recyclerView.adapter=adapter

        home_btn.setOnClickListener{
            val intent = Intent(this@MyBooksActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        help_btn.setOnClickListener {
            popupImg.visibility=View.VISIBLE
            totalView.setOnClickListener {
                popupImg.visibility=View.GONE
            }
        }
    }

    private fun initData(){
        val scan = Scanner(resources.openRawResource(R.raw.my_books))
        while(scan.hasNextLine()){
            val imgName = scan.nextLine()
            val title=scan.nextLine()
            val id = resources.getIdentifier(imgName, "drawable", packageName)
            val cover= ContextCompat.getDrawable(this, id)

            data.add(
                BookData(
                    title,
                    cover!!,
                    "sunmoon"
                )
            )
        }
        scan.close()
    }
}