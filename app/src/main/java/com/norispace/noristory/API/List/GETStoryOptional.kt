package com.norispace.noristory.API.List

import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.OptionalStory_Model
import com.norispace.noristory.Model.StoryTitle_Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GETStoryOptional {
    fun call(title: String, onResponse: (Response<List<OptionalStory_Model>?>) -> Unit, onFailure: (Throwable) -> Unit) {
        RetrofitClient.API.getOptionalStory(title).enqueue(object : Callback<List<OptionalStory_Model>?> {
            override fun onFailure(call: Call<List<OptionalStory_Model>?>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<List<OptionalStory_Model>?>, response: Response<List<OptionalStory_Model>?>) {
                onResponse(response)
            }

        })

    }

}