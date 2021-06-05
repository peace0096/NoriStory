package com.norispace.noristory

data class SubjectStoryData(
    var page: Int, //content 페이지
    var sizeX: Int, //content 크기
    var sizeY: Int,
    var locationX: Int, //content 위치
    var locationY: Int,
    var contentType:Int, //1은 이미지, 2는 텍스트
    var content:String) {
}