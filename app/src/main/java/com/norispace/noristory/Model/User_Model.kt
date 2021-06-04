package com.norispace.noristory.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User_Model (
    @SerializedName("name")
    @Expose
    var name:String?,
    @SerializedName("gender")
    @Expose
    var gender:String?,
    @SerializedName("birthday")
    @Expose
    var birthday:String?
)