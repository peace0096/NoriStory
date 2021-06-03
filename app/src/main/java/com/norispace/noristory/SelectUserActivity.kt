package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.databinding.ActivitySelectUserBinding

class SelectUserActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.apply {
            backBtn?.setOnClickListener {
                val intent= Intent(this@SelectUserActivity,MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            makeAccount?.setOnClickListener {
                val intent= Intent(this@SelectUserActivity,SignUpActivity::class.java)
                startActivity(intent)
            }
            user1?.setOnClickListener {
                val intent= Intent(this@SelectUserActivity,MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }
    }
}