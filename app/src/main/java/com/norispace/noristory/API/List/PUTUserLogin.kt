package com.norispace.noristory.API.List

import android.util.Log
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.User_Model
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PUTUserLogin {
    fun call(name:String, gender:String, birthday:String, callback: RetrofitClient.callback ) {
        val body = JSONObject()
        val bod1 = User_Model(name, gender, birthday)
        try {
            body.put("name", name)
            body.put("gender", gender)
            body.put("birthday", birthday)
        } catch (e: JSONException) {
            callback.callbackMethod(false, null)
        }

        RetrofitClient.getBaseClient().login(bod1)
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