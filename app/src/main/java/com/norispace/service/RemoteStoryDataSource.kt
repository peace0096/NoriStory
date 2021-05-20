package com.norispace.service

import android.util.Log
import com.norispace.pojo.Story.OptionalStoryResult
import com.norispace.pojo.Story.StoryTitleCoverResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteStoryDataSource : RemoteDataSource {
    override fun getStoryTitleCover(
        category: String,
        onResponse: (Response<StoryTitleCoverResult>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        RetrofitClient.service.getStoryTitleCover(category).enqueue(object : Callback<StoryTitleCoverResult> {
            override fun onFailure(call: Call<StoryTitleCoverResult>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<StoryTitleCoverResult>,
                response: Response<StoryTitleCoverResult>
            ) {
                onResponse(response)
            }
        })
    }

    override fun getOptionalStory(
        title: String,
        page: Int?,
        onResponse: (Response<OptionalStoryResult>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        if (page != null) {
            RetrofitClient.service.getOptionalStory(title, page).enqueue(object : Callback<OptionalStoryResult> {
                override fun onFailure(call: Call<OptionalStoryResult>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<OptionalStoryResult>,
                    response: Response<OptionalStoryResult>
                ) {
                    onResponse(response)

                }
            })
        }
        else {
            RetrofitClient.service.getOptionalStory(title, null).enqueue(object : Callback<OptionalStoryResult> {
                override fun onFailure(call: Call<OptionalStoryResult>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<OptionalStoryResult>,
                    response: Response<OptionalStoryResult>
                ) {
                    onResponse(response)
                }
            })
        }
    }
}