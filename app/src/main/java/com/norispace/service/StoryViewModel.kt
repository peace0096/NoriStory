package com.norispace.service

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.norispace.pojo.Story.OptionalStoryResponse
import com.norispace.pojo.Story.StoryTitleCoverResponse

class StoryViewModel(context: Context) : ViewModel() {

    val StoryRepository = StoryRepository()
    val S3Helper = S3Helper(context)

    val isSuccessGet = MutableLiveData<Boolean>()
    val storyTitleCoverList = MutableLiveData<List<StoryTitleCoverResponse>>()
    val optionalStoryList = MutableLiveData<List<OptionalStoryResponse>>()

    fun getStoryTitleCover(category: String) {
        var result : List<StoryTitleCoverResponse>? = null
        StoryRepository.getStoryTitleCover(category = category,
        onResponse = {

            if(it.isSuccessful) {
                result = it.body()!!.storytitlecover
                storyTitleCoverList.value = result
                isSuccessGet.value = true
            }

        },
        onFailure = {
            isSuccessGet.value = false

        }

        )

    }

    fun getOptionalStory(title: String, page: Int?) {
        var result : List<OptionalStoryResponse>? = null
        if (page != null) {
            StoryRepository.getOptionalStory(
                title = title, page = page,
                onResponse = {
                    if(it.isSuccessful) {
                        result = it.body()!!.optionalstory
                        optionalStoryList.value = result
                        isSuccessGet.value = true
                    }

                },
                onFailure = {
                    isSuccessGet.value = false
                }

            )
        } else {
            StoryRepository.getOptionalStory(
                title = title, page = null,
                onResponse = {
                    if(it.isSuccessful) {
                        result = it.body()!!.optionalstory
                        optionalStoryList.value = result
                    }

                },
                onFailure = {
                    isSuccessGet.value = false
                }

            )
        }
    }


}