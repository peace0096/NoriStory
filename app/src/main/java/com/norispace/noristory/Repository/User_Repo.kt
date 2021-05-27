package com.norispace.noristory.Repository

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User

class User_Repo {

    private var model:OAuthToken? = null

    companion object {
        @Volatile
        private var instance : User_Repo? = null

        @JvmStatic
        fun getInstance(): User_Repo = instance ?: synchronized(this) {
            instance ?: User_Repo().also {
                instance = it
            }
        }
    }

    fun setModel(model:OAuthToken) {
        this.model = model
    }

    fun getModel() : OAuthToken? {
        return this.model
    }


    fun login(context:Context, callback:(OAuthToken?, Throwable?) -> Unit ) {

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    fun logout(callback:(Throwable?) -> Unit) {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e("Logout", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                callback(error)
            }
            else {
                Log.i("Logout", "로그아웃 성공. SDK에서 토큰 삭제됨")
                callback(error)
            }
        }
    }

}