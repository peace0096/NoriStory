package com.norispace.noristory.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.SubjectStoryData
import com.norispace.noristory.Model.SubjectStoryThumbnail_Model
import com.norispace.noristory.Model.SubjectStory_Model
import com.norispace.noristory.Repository.Story_Repo
import com.norispace.noristory.Repository.User_Repo

import org.json.JSONArray
import org.json.JSONObject

class StoryViewModel {
    val subjectstorydatalistmodel= MutableLiveData<ArrayList<SubjectStoryData>>()
    val subjectstorymodellistmodel = MutableLiveData<ArrayList<SubjectStory_Model>>()
    val subjectstorythumbnailmodellistmodel = MutableLiveData<ArrayList<SubjectStoryThumbnail_Model>>()

    val sharedstorydatalistmodel= MutableLiveData<ArrayList<SubjectStoryData>>()
    val sharedstorymodellistmodel = MutableLiveData<ArrayList<SubjectStory_Model>>()
    val sharedstorythumbnailmodellistmodel = MutableLiveData<ArrayList<SubjectStoryThumbnail_Model>>()

    fun insertSubjectStoryContent(title:String, data:SubjectStoryData) {
        Story_Repo.callPostSubjectStoryContent(title, data, object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        //성공
                    }

                }
            }


        })

    }

    fun getSubjectStoryContent(title:String) {
        Story_Repo.callGetSubjectStoryContent(title, object:RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        val list = ArrayList<SubjectStoryData>()
                        val gson = Gson()
                        val jsonArray = JSONArray(result)
                        for(i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
//                            val title = jsonObject.getString("title")
//                            val name = jsonObject.getString("name")
//                            val page = jsonObject.getInt("page")
//                            val sizeX = jsonObject.getInt("sizeX")
//                            val sizeY = jsonObject.getInt("sizeY")
//                            val locationX = jsonObject.getInt("locationX")
//                            val locationY = jsonObject.getInt("locationY")
//                            val contentType = jsonObject.getInt("contentType")
//                            val content = jsonObject.getString("content")
                            list.add(gson.fromJson(jsonObject.toString(), SubjectStoryData::class.java))
                        }
                        Story_Repo.setSubjectStoryDataListModel(list)
                        subjectstorydatalistmodel.value = Story_Repo.getSubjectStoryDataListModel()

                    }
                }
            }
        })
    }

    fun deleteSubjectStoryContent(title:String, data:SubjectStoryData) {
        Story_Repo.callDeleteSubjectStoryContent(title, data, object:RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun insertSubjectStory(data:SubjectStory_Model) {
        Story_Repo.callPostSubjectStory(data, object:RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun getSubjectStory(title:String) {
        Story_Repo.callGetSubjectStory(title, object :RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        val list = ArrayList<SubjectStory_Model>()
                        val gson = Gson()
                        val jsonArray = JSONArray(result)
                        for(i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray[i]
                            val js = gson.fromJson(jsonObject.toString(), SubjectStory_Model::class.java)
                            Log.d("subject data", js.page.toString())
                            list.add(js)
                        }
                        Story_Repo.setSubjectStoryModelListModel(list)
                        subjectstorymodellistmodel.value = Story_Repo.getSubjectStoryModelListModel()
                    }
                }
            }
        })
    }

    fun deleteSubjectStory(data:SubjectStory_Model) {
        Story_Repo.callDeleteSubjectStory(data, object :RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun insertSubjectStoryThumbnail(data:SubjectStoryThumbnail_Model) {
        Story_Repo.callPostSubjectStoryThumbnail(data, object :RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun getSubjectStoryThumbnail() {
        Story_Repo.callGetSubjectStoryThumbnail(object : RetrofitClient.callback{
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {

                if(isSuccessful) {
                    if(result != null) {
                        val list = ArrayList<SubjectStoryThumbnail_Model>()
                        val gson = Gson()
                        val jsonArray = JSONArray(result)
                        for(i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray[i]
                            list.add(gson.fromJson(jsonObject.toString(), SubjectStoryThumbnail_Model::class.java))
                        }
                        Story_Repo.setSubjectStoryThumbnailListModel(list)
                        subjectstorythumbnailmodellistmodel.value = Story_Repo.getSubjectStoryThumbnailListModel()
                    }

                }
                else {
                }
            }
        })
    }

    fun deleteSubjectStoryThumbnail(data:SubjectStoryThumbnail_Model) {
        Story_Repo.callDeleteSubjectStoryThumbnail(data, object : RetrofitClient.callback{
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun insertSharedStory(data:SubjectStory_Model) {
        Story_Repo.callPostSharedtStory(data, object : RetrofitClient.callback{
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun getSharedStory(token:String, title : String) {
        Story_Repo.callGetSharedStory(token, title, object : RetrofitClient.callback{
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        val list = ArrayList<SubjectStory_Model>()
                        val gson = Gson()
                        val jsonArray = JSONArray(result)
                        for(i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray[i]
                            list.add(gson.fromJson(jsonObject.toString(), SubjectStory_Model::class.java))
                        }
                        Story_Repo.setSharedStoryModelListModel(list)
                        sharedstorymodellistmodel.value = Story_Repo.getSharedStoryModelListModel()
                    }
                }
            }
        })
    }

    fun deleteSharedStory(data:SubjectStory_Model) {
        Story_Repo.callDeleteSharedStory(data, object :RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun insertSharedStoryThumbnail(data:SubjectStoryThumbnail_Model) {
        Story_Repo.callPostSharedStoryThumbnail(data, object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }

    fun getSharedStoryThumbnail() {
        Story_Repo.callGetSubjectStoryThumbnail(object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {
                        val list = ArrayList<SubjectStoryThumbnail_Model>()
                        val gson = Gson()
                        val jsonArray = JSONArray(result)
                        for(i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray[i]
                            list.add(gson.fromJson(jsonObject.toString(), SubjectStoryThumbnail_Model::class.java))
                        }
                        Story_Repo.setSharedStoryThumbnailListModel(list)
                        sharedstorythumbnailmodellistmodel.value = Story_Repo.getSharedStoryThumbnailListModel()
                    }
                }
            }
        })
    }

    fun deleteSharedStoryThumbnail(data:SubjectStoryThumbnail_Model) {
        Story_Repo.callDeleteSharedStoryThumbnail(data, object : RetrofitClient.callback {
            override fun callbackMethod(isSuccessful: Boolean, result: String?) {
                if(isSuccessful) {
                    if(result != null) {

                    }
                }
            }
        })
    }


}