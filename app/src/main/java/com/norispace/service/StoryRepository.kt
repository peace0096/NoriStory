package com.norispace.service

import com.norispace.pojo.Story.OptionalStoryResult
import com.norispace.pojo.Story.StoryTitleCoverResult
import retrofit2.Response

class StoryRepository {
    val retrofitRemoteStoryDataSource: RemoteDataSource = RemoteStoryDataSource()
    fun getStoryTitleCover(category: String,
                           onResponse: (Response<StoryTitleCoverResult>) -> Unit,
                           onFailure: (Throwable) -> Unit) {
        retrofitRemoteStoryDataSource.getStoryTitleCover(category, onResponse, onFailure)
    }

    fun getOptionalStory(
        title: String, page: Int?,
        onResponse: (Response<OptionalStoryResult>) -> Unit,
        onFailure: (Throwable) -> Unit) {
        if (page != null) {
            retrofitRemoteStoryDataSource.getOptionalStory(title, page, onResponse, onFailure)
        }
        else {
            retrofitRemoteStoryDataSource.getOptionalStory(title, null, onResponse, onFailure)
        }
    }
}