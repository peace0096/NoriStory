package com.norispace.noristory.ListFragment

import android.content.Context
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
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.norispace.noristory.*
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.Model.SubjectStoryThumbnail_Model
import com.norispace.noristory.MyBooksActivity2
import com.norispace.noristory.R
import com.norispace.noristory.Repository.Story_Repo
import com.norispace.noristory.Repository.User_Repo
import com.norispace.noristory.ViewModel.StoryViewModel
import com.norispace.noristory.databinding.FragmentBookItemBinding
import kotlinx.android.synthetic.main.activity_my_books.view.*
import java.io.File


class BookItemFragment : Fragment() {

    private var columnCount = 3
    private var data = ArrayList<SubjectStoryThumbnail_Model>()
    private lateinit var dbHelper:DBHelper
    private lateinit var storyViewModel:StoryViewModel
    var btn = -1
    val myAdapter=MyBookItemAdapter(data)
    lateinit var binding:FragmentBookItemBinding

    override fun onStop() {
        super.onStop()
        data.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_item_list, container, false)

        binding = FragmentBookItemBinding.inflate(layoutInflater)
        binding.apply {
            if (arguments != null)
                btn = arguments!!.getInt("btn")

            initData()
            initObserve()
            // Set the adapter
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    myAdapter.itemClickListener = object :
                        MyBookItemAdapter.OnItemClickListener {
                        override fun OnHeartClick(
                            holder: MyBookItemAdapter.ViewHolder,
                            view: View,
                            position: Int
                        ) {
                            if (myAdapter.checkAry[position] == 0) {
                                holder.heart.setImageResource(R.drawable.heart_filled)
                                myAdapter.checkAry[position] = 1
                            } else {
                                holder.heart.setImageResource(R.drawable.heart_empty)
                                myAdapter.checkAry[position] = 0
                            }
                        }

                        override fun OnBookClick(
                            holder: MyBookItemAdapter.ViewHolder,
                            view: View,
                            position: Int
                        ) {
                            if (btn == 0) {
                                val intent = Intent(activity, MyBooksActivity2::class.java)
                                intent.putExtra("title", data[position].title)
                                startActivity(intent)
                            } else if (btn == 1) {
                                val intent = Intent(activity, LibraryActivity::class.java)
                                intent.putExtra("title", data[position].title)
                                startActivity(intent)
                            }
                            data.clear()
                        }
                    }

                    myAdapter.set = object :
                        MyBookItemAdapter.OnSetting {
                        override fun onHeartSetting(holder: MyBookItemAdapter.ViewHolder): Boolean {
                            if (btn == 0)
                                return true
                            return false
                        }
                    }

                    adapter = myAdapter

                }
            }
            return view
        }
    }

    private fun initObserve() {
        storyViewModel.subjectstorythumbnailmodellistmodel.observe(this, Observer {
            Log.d("hello0", "hello")
            data.addAll(it)
            var list:ArrayList<SubjectStoryThumbnail_Model>? = null
            if(btn == 0) {
                //??????
                //list = dbHelper.getAllSubjectStoryThumbnail()
            }

//            if(list != null) {
//                for(e in list) {
//                    var flag = true
//                    for(i in 0 until data.size) {
//                        if(e.title == data[i].title) {
//                            flag = false
//                            break
//                        }
//
//                    }
//                    if(flag) {
//                        data.add(e)
//                    }
//                }
//            }

            myAdapter.notifyDataSetChanged()
        })
    }

    private fun initData(){
        storyViewModel = StoryViewModel()
        dbHelper = DBHelper(context!!)
        if(btn == 0)
        {
            data.clear()
            storyViewModel.getSubjectStoryThumbnail()

//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"????????? ??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
        }
        else
        {
            data.clear()
            storyViewModel.getSubjectStoryThumbnail()
            //data.addAll(Story_Repo.getSharedStoryThumbnailListModel())
            //storyViewModel.getSharedStoryThumbnail()
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"?????????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
//            data.add(SharedBookData("temp",resources.getDrawable(R.drawable.signup_boy),"??????","????????????"))
        }

    }
}