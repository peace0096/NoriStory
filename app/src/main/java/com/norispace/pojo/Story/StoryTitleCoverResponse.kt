package com.norispace.pojo.Story

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoryTitleCoverResponse {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("coverimage")
    @Expose
    var coverimage: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null
}