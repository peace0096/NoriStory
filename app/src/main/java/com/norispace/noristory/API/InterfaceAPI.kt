package com.norispace.noristory.API

import com.norispace.noristory.Model.OptionalStory_Model
import com.norispace.noristory.Model.Result_Model
import com.norispace.noristory.Model.StoryTitle_Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceAPI {
    @GET("Story/getStoryTitleCover")
    fun getStoryTitleCover(@Query("category")query: String): Call<List<StoryTitle_Model>>

    @GET("Story/getOptionalStory")
    fun getOptionalStory(@Query("title") query: String): Call<List<OptionalStory_Model>?>
}