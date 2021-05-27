package com.norispace.noristory.API.List

import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.Result_Model
import com.norispace.noristory.Model.StoryTitle_Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GETStoryTitleCover {
    fun call(category: String, onResponse: (Response<List<StoryTitle_Model>?>) -> Unit, onFailure: (Throwable) -> Unit) {
        RetrofitClient.API.getStoryTitleCover(category).enqueue(object : Callback<List<StoryTitle_Model>> {
            override fun onFailure(call: Call<List<StoryTitle_Model>?>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<List<StoryTitle_Model>?>, response: Response<List<StoryTitle_Model>?>) {
                onResponse(response)
            }

        })

    }
}