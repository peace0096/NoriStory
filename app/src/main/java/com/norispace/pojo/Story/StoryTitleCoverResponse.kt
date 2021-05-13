package com.norispace.pojo.Story

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoryTitleCoverResponse {
    @SerializedName("result")
    @Expose
    var result: List<Result>? = null

}