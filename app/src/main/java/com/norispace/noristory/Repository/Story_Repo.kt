package com.norispace.noristory.Repository

import com.norispace.noristory.API.List.DELETE.*
import com.norispace.noristory.API.List.GET.*
import com.norispace.noristory.API.List.POST.*
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.SubjectStoryData
import com.norispace.noristory.Model.SubjectStoryThumbnail_Model
import com.norispace.noristory.Model.SubjectStory_Model

object Story_Repo {

    private lateinit var subjectstorydatalistmodel:ArrayList<SubjectStoryData>
    private lateinit var subjectstorymodellistmodel:ArrayList<SubjectStory_Model>
    private lateinit var subjectstorythumbnailmodellistmodel:ArrayList<SubjectStoryThumbnail_Model>

    fun setSubjectStoryDataListModel(model:ArrayList<SubjectStoryData>) {
        this.subjectstorydatalistmodel = model
    }

    fun getSubjectStoryDataListModel():ArrayList<SubjectStoryData> {
        return this.subjectstorydatalistmodel
    }

    fun setSubjectStoryModelListModel(model: ArrayList<SubjectStory_Model>) {
        this.subjectstorymodellistmodel = model
    }

    fun getSubjectStoryModelListModel() : ArrayList<SubjectStory_Model> {
        return this.subjectstorymodellistmodel
    }

    fun setSubjectStoryThumbnailListModel(model: ArrayList<SubjectStoryThumbnail_Model>)  {
        this.subjectstorythumbnailmodellistmodel = model
    }

    fun getSubjectStoryThumbnailListModel() : ArrayList<SubjectStoryThumbnail_Model> {
        return this.subjectstorythumbnailmodellistmodel
    }

    fun callPostSubjectStoryContent(title:String, data: SubjectStoryData, callback: RetrofitClient.callback) {
        PostSubjectStoryContentAPI.call(title, data, callback)
    }

    fun callGetSubjectStoryContent(title: String, callback: RetrofitClient.callback) {
        GetSubjectStoryContentAPI.call(title, callback)
    }

    fun callDeleteSubjectStoryContent(title:String, data: SubjectStoryData, callback: RetrofitClient.callback) {
        DeleteSubjectStoryContentAPI.call(title, data, callback)
    }

    fun callPostSubjectStory(data: SubjectStory_Model, callback: RetrofitClient.callback) {
        PostSubjectStoryAPI.call(data, callback)
    }

    fun callGetSubjectStory(title:String, callback: RetrofitClient.callback) {
        GetSubjectStoryAPI.call(title, callback)
    }

    fun callDeleteSubjectStory(data: SubjectStory_Model, callback: RetrofitClient.callback) {
        DeleteSubjectStoryAPI.call(data, callback)
    }

    fun callPostSubjectStoryThumbnail(data:SubjectStoryThumbnail_Model, callback: RetrofitClient.callback) {
        PostSubjectStoryThumbnailAPI.call(data, callback)
    }

    fun callGetSubjectStoryThumbnail(callback: RetrofitClient.callback) {
        GetSubjectStoryThumbnailAPI.call(callback)
    }

    fun callDeleteSubjectStoryThumbnail(data: SubjectStoryThumbnail_Model, callback: RetrofitClient.callback) {
        DeleteSubjectStoryThumbnailAPI.call(data, callback)
    }

    fun callPostSharedtStory(data: SubjectStory_Model, callback: RetrofitClient.callback) {
        PostSharedStoryAPI.call(data, callback)
    }

    fun callGetSharedStory(title:String, callback: RetrofitClient.callback) {
        GetSharedStoryAPI.call(title, callback)
    }

    fun callDeleteSharedStory(data: SubjectStory_Model, callback: RetrofitClient.callback) {
        DeleteSharedStoryAPI.call(data, callback)
    }

    fun callPostSharedStoryThumbnail(data:SubjectStoryThumbnail_Model, callback: RetrofitClient.callback) {
        PostSharedThumbnailAPI.call(data, callback)
    }

    fun callGetSharedStoryThumbnail(callback: RetrofitClient.callback) {
        GetSharedStoryThumbnailAPI.call(callback)
    }

    fun callDeleteSharedStoryThumbnail(data: SubjectStoryThumbnail_Model, callback: RetrofitClient.callback) {
        DeleteSharedStoryThumbnailAPI.call(data, callback)
    }


}