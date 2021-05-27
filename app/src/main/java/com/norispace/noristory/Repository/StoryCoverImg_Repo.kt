package com.norispace.noristory.Repository

import com.norispace.noristory.API.List.GETStoryTitleCover
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.Result_Model
import com.norispace.noristory.Model.StoryTitle_Model
import retrofit2.Response

class StoryCoverImg_Repo {

    private lateinit var model : List<StoryTitle_Model>

    companion object {
        @Volatile
        private var instance : StoryCoverImg_Repo? = null

        @JvmStatic
        fun getInstance(): StoryCoverImg_Repo = instance ?: synchronized(this) {
            instance ?: StoryCoverImg_Repo().also {
                instance = it
            }
        }

    }

    fun getModel() : List<StoryTitle_Model>? {
        return this.model
    }

    fun setModel(model : List<StoryTitle_Model>?) {
        if (model != null) {
            this.model = model
        }
    }

    fun callGetStoryTitleCover(category: String, onResponse: (Response<List<StoryTitle_Model>?>) -> Unit, onFailure: (Throwable) -> Unit) {
        GETStoryTitleCover.call(category, onResponse, onFailure)
    }

}