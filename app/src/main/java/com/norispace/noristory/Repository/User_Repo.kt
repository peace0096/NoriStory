package com.norispace.noristory.Repository

import com.norispace.noristory.API.List.GET.GETUserMe
import com.norispace.noristory.API.List.PUT.PUTUserLogin
import com.norispace.noristory.API.RetrofitClient
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
        PUTUserLogin.call(name, gender, birthday, callback)
    }

    fun callGetUserMe(token:String, callback:RetrofitClient.callback) {
        GETUserMe.call(token, callback)
    }


}