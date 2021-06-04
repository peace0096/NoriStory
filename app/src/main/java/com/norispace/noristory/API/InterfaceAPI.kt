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


    @Headers("Content-Type: application/json")
    @PUT("User/card/")
    fun insertCard(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @GET("User/card/")
    fun getAllCard(
        @Header("Authorization") token : String
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @DELETE("User/card/")
    fun deleteCard(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>


    @Headers("Content-Type: application/json")
    @PUT("User/subjectStoryContent/")
    fun insertContent(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @GET("User/subjectStoryContent/")
    fun getAllContent(
        @Header("Authorization") token : String,
        @Body body:String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @DELETE("User/subjectStoryContent/")
    fun deleteContent(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>


    @Headers("Content-Type: application/json")
    @PUT("User/subjectStory/")
    fun insertSubjectStory(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @GET("User/subjectStory/")
    fun getAllSubjectStory(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @DELETE("User/subjectStory/")
    fun deleteSubjectStory(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @PUT("Story/sharedStory/")
    fun insertSharedStory(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @GET("Story/sharedStory/")
    fun getAllSharedStory(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @DELETE("Story/sharedStory/")
    fun deleteSharedStory(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @PUT("Story/sharedStoryThumbnail/")
    fun insertSharedStoryThumbnail(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @GET("Story/sharedStoryThumbnail/")
    fun getAllSharedStoryThumbnail(
        @Header("Authorization") token : String
    ): Call<String?>

    @Headers("Content-Type: application/json")
    @DELETE("Story/sharedStoryThumbnail/")
    fun deleteSharedStoryThumbnail(
        @Header("Authorization") token : String,
        @Body body: String?
    ): Call<String?>

}