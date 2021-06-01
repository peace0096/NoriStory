package com.norispace.noristory.API

import com.norispace.noristory.Model.User_Model
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface InterfaceAPI {

    @Headers("Content-Type: application/json")
    @GET("User/getMe")
    fun getUserMe(
        @Header("Authorization") token : String
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @PUT("User/login")
    fun login(
        @Body body: User_Model
    ): Call<String?>
}