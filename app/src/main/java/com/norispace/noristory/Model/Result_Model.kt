package com.norispace.noristory.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result_Model {
    @SerializedName("storycoverimage")
    @Expose
    var result_books: List<StoryTitle_Model>? = null

    @SerializedName("optionalstory")
    @Expose
    var result_optional: List<OptionalStory_Model>? = null

}