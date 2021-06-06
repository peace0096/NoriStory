package com.norispace.noristory.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.SubjectStory_Model
import com.norispace.noristory.Model.User_Model
import com.norispace.noristory.Repository.Story_Repo
import com.norispace.noristory.Repository.User_Repo
import org.json.JSONArray
import org.json.JSONObject

class UserViewModel {
    val tokenmodel = MutableLiveData<String>()
    val usermodel = MutableLiveData<User_Model>()
    val cardmodel = MutableLiveData<List<String>>()

    fun login(name:String, gender:String, birthday:String) {
        User_Repo.callPostUserLogin(name, gender, birthday, object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        val gson = Gson()
                        val jsonObject = JSONObject(result)
                        User_Repo.setToken(jsonObject.get("token").toString())
                        tokenmodel.value = User_Repo.getToken()
                    }
                }
                else {
                    Log.i("login err", result.toString())
                }
            }


        })

    }

    fun getUserMe(token:String) {
        User_Repo.callGetUserMe(token, object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        val gson = Gson()
                        val jsonObject = JSONObject(result)
                        val name = jsonObject.get("name").toString()
                        val gender = jsonObject.get("gender").toString()
                        val birthday = jsonObject.get("birthday").toString()

                        User_Repo.setModel(User_Model(name, gender, birthday))
                        usermodel.value = User_Repo.getModel()
                    }
                }
                else {
                    Log.i("getUserMe err", result.toString())
                }
            }


        })
    }

    fun insertCard(card:String) {
        User_Repo.callPostUserCard(card, object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {


                    }
                }
            }
        })
    }

    fun getCard() {
        User_Repo.callGetUserCard(object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        val list = ArrayList<String>()
                        val gson = Gson()
                        val jsonArray = JSONArray(result)
                        for(i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray[i] as JSONObject
                            list.add(jsonObject.get("name").toString())
                        }
                        User_Repo.setCardModel(list)
                        cardmodel.value = User_Repo.getCardModel()
                    }
                }
            }
        })
    }

}