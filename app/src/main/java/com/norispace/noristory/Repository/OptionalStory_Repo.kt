package com.norispace.noristory.Repository

import com.norispace.noristory.API.List.GETStoryOptional
import com.norispace.noristory.API.List.GETStoryTitleCover
import com.norispace.noristory.API.RetrofitClient
import com.norispace.noristory.Model.OptionalStory_Model
import com.norispace.noristory.Model.Result_Model
import com.norispace.noristory.Model.StoryTitle_Model
import retrofit2.Response

class OptionalStory_Repo {
    private lateinit var model : List<OptionalStory_Model>

    companion object {
        @Volatile
        private var instance : OptionalStory_Repo? = null

        @JvmStatic
        fun getInstance(): OptionalStory_Repo = instance ?: synchronized(this) {
            instance ?: OptionalStory_Repo().also {
                instance = it
            }
        }

    }

    fun getModel() : List<OptionalStory_Model>? {
        return this.model
    }

    fun setModel(model : List<OptionalStory_Model>?) {
        if (model != null) {
            this.model = model
        }
    }

    fun callGetStoryOptional(title: String, onResponse: (Response<List<OptionalStory_Model>?>) -> Unit, onFailure: (Throwable) -> Unit) {
        GETStoryOptional.call(title, onResponse, onFailure)
    }
}