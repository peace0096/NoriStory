package com.norispace.noristory.User

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.model.User
import com.norispace.noristory.Repository.User_Repo

class UserViewModel {
    private var token:MutableLiveData<OAuthToken>? = null
    private var user:MutableLiveData<User>? = null

    fun login(context: Context) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("Callback", "로그인 실패", error)
            }
            else if (token != null) {
                Log.i("Callback", "로그인 성공 ${token.accessToken}")
                User_Repo.getInstance().setModel(token)
                this.token?.value = User_Repo.getInstance().getModel()
            }
        }
        User_Repo.getInstance().login(context, callback)
    }

    fun logout() {
        val callback:(Throwable?) -> Unit = { error ->
            if(error !=  null) {
                Log.e("Callback", "로그아웃 실패", error)
            }
            else {
                //로그아웃 성공
            }
        }
        User_Repo.getInstance().logout(callback)
    }

    fun getUserInfoMe() {
        val callback:(User?, Throwable?) -> Unit = { user, error ->
            if(error != null) {
                Log.e("Callback", "카카오 유저 정보가져오기 실패", error)
            }
            else {
                this.user?.value = user
            }
        }
    }

}