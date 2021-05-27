package com.norispace.noristory.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoryTitle_Model {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("title_kor")
    @Expose
    var title_kor: String? = null

    @SerializedName("coverimage")
    @Expose
    var coverimage: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null
}