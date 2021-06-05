package com.norispace.noristory.API.List.DELETE

import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Repository.User_Repo
import com.norispace.noristory.SubjectStoryData
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DeleteSubjectStoryContentAPI {
    fun call(title:String, data: SubjectStoryData, callback: RetrofitClient.callback) {
        val body = JSONObject()
        try {
            body.put("title", title)
            body.put("page", data.page)
            body.put("locationX", data.locationX)
            body.put("locationY", data.locationY)
        } catch (e: JSONException) {
            callback.callbackMethod(false, "parse error")
        }
        RetrofitClient.getBaseClient().deleteCard(User_Repo.getToken(), body.toString()).enqueue(object:
            Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                try {
                    if (response.isSuccessful) {
                        callback.callbackMethod(true, response.body().toString())
                    } else {
                        val jsonObject = JSONObject(response.errorBody()?.string())
                        callback.callbackMethod(false, jsonObject.getString("message"))
                    }
                } catch (e: JSONException) {
                    callback.callbackMethod(false,  e.message.toString())
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                callback.callbackMethod(false, t.message.toString())
            }
        })
    }
}