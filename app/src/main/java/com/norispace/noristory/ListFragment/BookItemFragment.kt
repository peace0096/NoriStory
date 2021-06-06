package com.norispace.noristory.ListFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.norispace.noristory.MyBooksActivity2
import com.norispace.noristory.R


class BookItemFragment : Fragment() {

    private var columnCount = 3
    private var data = ArrayList<SharedBookData>()
    var btn = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_item_list, container, false)

        if(arguments!=null)
            btn = arguments!!.getInt("btn")

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                initData()
                val myAdapter=MyBookItemAdapter(data)
                myAdapter.itemClickListener = object :
                    MyBookItemAdapter.OnItemClickListener {
                    override fun OnHeartClick(
                        holder: MyBookItemAdapter.ViewHolder,
                        view: View,
                        position: Int
                    ) {
                        if(myAdapter.checkAry[position]==0){
                            holder.heart.setImageResource(R.drawable.heart_filled)
                            myAdapter.checkAry[position]=1
                        }else{
                            holder.heart.setImageResource(R.drawable.heart_empty)
                            myAdapter.checkAry[position]=0
                        }
                    }

                    override fun OnBookClick(
                        holder: MyBookItemAdapter.ViewHolder,
                        view: View,
                        position: Int
                    ) {
                        if(btn == 0)
                        {
                            val intent = Intent(activity, MyBooksActivity2::class.java)
                            intent.putExtra("title", data[position].title)
                            startActivity(intent)
                        }

                    }

                }
                myAdapter.set = object :
                    MyBookItemAdapter.OnSetting{
                    override fun onHeartSetting(holder: MyBookItemAdapter.ViewHolder): Boolean {
                        if(btn == 0)
                            return true
                        return false
                    }

                }
                adapter = myAdapter

            }
        }
        return view
    }
    private fun initData(){
        if(btn == 0)
        {
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"나만의 서재","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        }
        else
        {
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"도서관","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"제목","작자미상"))
        }

    }
}