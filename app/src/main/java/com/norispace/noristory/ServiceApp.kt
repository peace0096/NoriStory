package com.norispace.noristory

import com.norispace.pojo.Story.StoryTitleCoverResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApp {
    @GET("Story/getStoryTitleCover")
    fun getStoryTitleCover(@Query("category")query: String): Call<StoryTitleCoverResponse>
}