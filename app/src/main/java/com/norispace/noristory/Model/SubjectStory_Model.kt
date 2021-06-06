package com.norispace.noristory.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubjectStory_Model(
    @SerializedName("title")
    @Expose
    var title:String,
    @SerializedName("page")
    @Expose
    var page:Int,
    @SerializedName("image")
    @Expose
    var image:String)