package com.norispace.pojo.Story

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OptionalStoryResult {
    @SerializedName("optionalstory")
    @Expose
    var optionalstory: List<OptionalStoryResponse>? = null

}