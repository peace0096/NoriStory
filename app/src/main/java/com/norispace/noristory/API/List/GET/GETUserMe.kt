package com.norispace.noristory.API.List.GET

import com.norispace.noristory.API.RetrofitClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GETUserMe {
    fun call(token:String, callback: RetrofitClient.callback ) {

        RetrofitClient.getBaseClient().getUserMe(token)
            .enqueue(object :
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