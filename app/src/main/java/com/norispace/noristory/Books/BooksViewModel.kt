package com.norispace.noristory.Books

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.Result_Model
import com.norispace.noristory.Model.StoryTitle_Model
import com.norispace.noristory.Repository.StoryCoverImg_Repo

class BooksViewModel {
    val books = MutableLiveData<List<StoryTitle_Model>>()

    companion object {
        @Volatile
        private var instance: BooksViewModel? = null

        @JvmStatic
        fun getInstance(): BooksViewModel = instance
            ?: synchronized(this) {
                instance
                    ?: BooksViewModel().also {
                        instance = it
                    }
            }


    }

    fun getBooks(category: String) {
        StoryCoverImg_Repo.getInstance()?.callGetStoryTitleCover(category = category,
            onResponse = {
                if (it.isSuccessful) {
                    Log.i("getBooks Response", "successful true")
                    StoryCoverImg_Repo.getInstance()?.setModel(it.body())
                    books.value = StoryCoverImg_Repo.getInstance().getModel()
                } else {
                    Log.i("getBooks Response", "successful false")
                }


            },
            onFailure = {
                Log.i("getBooks Failure", it.message.toString())
            })
    }

}