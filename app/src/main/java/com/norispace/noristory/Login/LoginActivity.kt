package com.norispace.noristory.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.norispace.noristory.MainActivity
import com.norispace.noristory.SelectUserActivity
import com.norispace.noristory.SignUpActivity
import com.norispace.noristory.User.UserViewModel
import com.norispace.noristory.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel

    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserve()
        init()
    }

    private fun initObserve() {
        userViewModel = UserViewModel()

        userViewModel.tokenmodel.observe(this, Observer {
            if(it != "none" && it != null) {
                Log.i("login", "성공!")
            }
            else {
                Log.i("login", "실패!")
            }

        })
    }

    private fun init() {
        binding.apply {

            loginNoris?.setOnClickListener {
                val i= Intent(baseContext, SignUpActivity::class.java)
                startActivity(i)
            }
        }
    }
}