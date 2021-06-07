package com.norispace.noristory.API.List.DELETE

import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.SubjectStory_Model
import com.norispace.noristory.Repository.User_Repo
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DeleteSubjectStoryAPI {
    fun call(model: SubjectStory_Model, callback: RetrofitClient.callback) {
        val body = JSONObject()
        try {
            body.put("title", model.title)
            body.put("page", model.page)

        } catch (e: JSONException) {
            callback.callbackMethod(false, "parse error")
        }
        RetrofitClient.getBaseClient().deleteSubjectStory(User_Repo.getToken(), model.title).enqueue(object:
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