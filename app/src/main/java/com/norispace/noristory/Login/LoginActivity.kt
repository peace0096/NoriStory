package com.norispace.noristory.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.norispace.noristory.R
import com.norispace.noristory.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("LoginAcitivity", "로그인 실패", error)
        }
        else if (token != null) {
            Log.i("LoginAcitivity", "로그인 성공 ${token.accessToken}")
        }
    }

    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            loginBtn?.setOnClickListener {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(baseContext)) {
                    UserApiClient.instance.loginWithKakaoTalk(baseContext, callback = callback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(baseContext, callback = callback)
                }
            }

        }
    }
}