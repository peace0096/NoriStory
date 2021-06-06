package com.norispace.noristory.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubjectStoryThumbnail_Model(
    @SerializedName("title")
    @Expose
    var title:String,
    @SerializedName("coverImage")
    @Expose
    var coverImage:String)