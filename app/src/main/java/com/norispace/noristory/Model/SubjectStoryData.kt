package com.norispace.noristory.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubjectStoryData(
    @SerializedName("page")
    @Expose
    var page: Int, //content 페이지
    @SerializedName("sizeX")
    @Expose
    var sizeX: Int, //content 크기
    @SerializedName("sizeY")
    @Expose
    var sizeY: Int,
    @SerializedName("locationX")
    @Expose
    var locationX: Int, //content 위치
    @SerializedName("locationY")
    @Expose
    var locationY: Int,
    @SerializedName("contentType")
    @Expose
    var contentType:Int, //1은 이미지, 2는 텍스트
    @SerializedName("content")
    @Expose
    var content:String) {
}