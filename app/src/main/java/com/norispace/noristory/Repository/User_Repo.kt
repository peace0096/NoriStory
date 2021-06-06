package com.norispace.noristory.Repository

import com.norispace.noristory.API.List.DELETE.DeleteSubjectStoryAPI
import com.norispace.noristory.API.List.DELETE.DeleteSubjectStoryContentAPI
import com.norispace.noristory.API.List.DELETE.DeleteUserCardAPI
import com.norispace.noristory.API.List.GET.GETUserMe
import com.norispace.noristory.API.List.GET.GetSubjectStoryAPI
import com.norispace.noristory.API.List.GET.GetSubjectStoryContentAPI
import com.norispace.noristory.API.List.GET.GetUserCardAPI
import com.norispace.noristory.API.List.POST.PostSubjectStoryAPI
import com.norispace.noristory.API.List.POST.PostSubjectStoryContentAPI
import com.norispace.noristory.API.List.POST.PostUserCardAPI
import com.norispace.noristory.API.List.POST.PostUserLoginAPI
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.SubjectStory_Model
import com.norispace.noristory.Model.User_Model


object User_Repo {

    private lateinit var usermodel:User_Model
    private var token : String = "none"

    fun getModel() : User_Model {
        return usermodel
    }

    fun setModel(model:User_Model) {
        this.usermodel = model
    }

    fun getToken() : String {
        return token
    }

    fun setToken(token: String) {
        this.token = token
    }


    fun callPostUserLogin(name : String, gender : String, birthday : String, callback: RetrofitClient.callback) {
        PostUserLoginAPI.call(name, gender, birthday, callback)
    }

    fun callGetUserMe(token:String, callback:RetrofitClient.callback) {
        GETUserMe.call(token, callback)
    }

    fun callPostUserCard(name:String, callback: RetrofitClient.callback) {
        PostUserCardAPI.call(name, callback)
    }

    fun callGetUserCard(callback: RetrofitClient.callback) {
        GetUserCardAPI.call(callback)
    }

    fun callDeleteUserCard(name:String, callback: RetrofitClient.callback) {
        DeleteUserCardAPI.call(name, callback)
    }





}