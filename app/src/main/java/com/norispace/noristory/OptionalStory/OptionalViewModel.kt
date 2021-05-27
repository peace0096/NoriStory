package com.norispace.noristory.OptionalStory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.OptionalStory_Model
import com.norispace.noristory.Model.Result_Model
import com.norispace.noristory.Repository.OptionalStory_Repo
import com.norispace.noristory.Repository.StoryCoverImg_Repo
import retrofit2.Response

class OptionalViewModel {
    val story = MutableLiveData<List<OptionalStory_Model>>()

    companion object {
        @Volatile
        private var instance: OptionalViewModel? = null

        @JvmStatic
        fun getInstance(): OptionalViewModel = instance ?: synchronized(this) {
            instance ?: OptionalViewModel().also {
                instance = it
            }
        }
    }

    fun getOptionalStory(title: String) {
        OptionalStory_Repo.getInstance()?.callGetStoryOptional(title,
            onResponse = {
                if(it.isSuccessful) {
                    Log.i("getOptionalStory Response", "successful true")
                    OptionalStory_Repo.getInstance().setModel(it.body())
                    story.value = OptionalStory_Repo.getInstance().getModel()
                }
                else {
                    Log.i("getOptionalStory Response", "successful false")
                }

            },
            onFailure = {
                Log.i("getOptionalStory Failure", it.message.toString())
            })
    }

}