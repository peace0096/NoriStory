package com.norispace.noristory.API.List.GET

import android.util.Log
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Repository.User_Repo
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GetSubjectStoryThumbnailAPI {
    fun call(callback: RetrofitClient.callback) {

        Log.d("callback", User_Repo.getToken())
        RetrofitClient.getBaseClient().getAllSubjectStoryThumbnail(User_Repo.getToken()).enqueue(object:
            Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                try {
                    if (response.isSuccessful) {
                        Log.d("success", response.body().toString())
                        callback.callbackMethod(true, response.body().toString())
                    } else {
                        val jsonObject = JSONObject(response.errorBody()?.string())
                        Log.d("err", jsonObject.getString("message"))
                        callback.callbackMethod(false, jsonObject.getString("message"))
                    }
                } catch (e: JSONException) {
                    Log.d("err", e.message.toString())
                    callback.callbackMethod(false,  e.message.toString())
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.d("err", t.message.toString())
                callback.callbackMethod(false, t.message.toString())
            }
        })
    }
}