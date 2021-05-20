package com.norispace.service


import com.norispace.pojo.Story.OptionalStoryResult
import com.norispace.pojo.Story.StoryTitleCoverResult
import retrofit2.Response


//레트로핏을 통해 통신하는 함수들을 모아둔 곳
interface RemoteDataSource {
    fun getStoryTitleCover(category:String, onResponse: (Response<StoryTitleCoverResult>) -> Unit,
                           onFailure: (Throwable)->Unit)

    fun getOptionalStory(
        title:String, page: Int?, onResponse: (Response<OptionalStoryResult>) -> Unit,
        onFailure: (Throwable) -> Unit)




}