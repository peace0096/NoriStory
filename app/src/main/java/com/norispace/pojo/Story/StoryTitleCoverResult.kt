package com.norispace.pojo.Story

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoryTitleCoverResult {
    @SerializedName("storytitlecover")
    @Expose
    var storytitlecover: List<StoryTitleCoverResponse>? = null

}