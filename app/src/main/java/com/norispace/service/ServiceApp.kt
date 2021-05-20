package com.norispace.service

import com.norispace.pojo.Story.OptionalStoryResult
import com.norispace.pojo.Story.StoryTitleCoverResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApp {
    @GET("Story/getStoryTitleCover")
    fun getStoryTitleCover(@Query("category")query: String): Call<StoryTitleCoverResult>

    @GET("Story/getOptionalStory")
    fun getOptionalStory(@Query("title") query: String, @Query("page") query1: Int?): Call<OptionalStoryResult>
}