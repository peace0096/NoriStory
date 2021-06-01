package com.norispace.noristory.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OptionalStory_Model(val title:String, val image:String, val page:Int, val nextPage1:Int, val nextPage2:Int, val undoPage:Int, val nextText1:String?, val nextText2:String?, val nextImage1:String?, val nextImage2:String?)