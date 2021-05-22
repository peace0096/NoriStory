package com.norispace.pojo.Story

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OptionalStoryResponse {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("nextPage1")
    @Expose
    var nextPage1: Int? = null

    @SerializedName("nextPage2")
    @Expose
    var nextPage2: Int? = null

    @SerializedName("undoPage")
    @Expose
    var undoPage: Int? = null

    @SerializedName("nextText1")
    @Expose
    var nextText1: String? = null

    @SerializedName("nextText2")
    @Expose
    var nextText2: String? = null

    @SerializedName("nextImage1")
    @Expose
    var nextImage1: String? = null

    @SerializedName("nextImage2")
    @Expose
    var nextImage2: String? = null
}