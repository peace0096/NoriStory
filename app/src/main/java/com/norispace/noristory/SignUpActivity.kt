package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.norispace.noristory.databinding.ActivitySignUpBinding
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    var page=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        binding.apply {
            frontBtn?.setOnClickListener {
                page++
                if(page==2){ //page == 2 -> 나이 , 캐릭터 선택
                    setAge?.visibility= View.VISIBLE
                    signupBoy?.setOnClickListener {
                        signupBoy.setImageResource(R.drawable.signup_boy_big)
                        signupGirl?.setImageResource(R.drawable.signup_girl_blur)
                    }
                    signupGirl?.setOnClickListener {
                        signupBoy?.setImageResource(R.drawable.signup_boy_blur)
                        signupGirl.setImageResource(R.drawable.signup_girl_big)
                    }
                }else if(page==3){ //로딩화면
                    loadingSecreen?.visibility=View.VISIBLE
                    val intent= Intent(this@SignUpActivity,MainActivity::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }

            }
            backBtn?.setOnClickListener {
                page--
                if (page==0){
                    val intent= Intent(this@SignUpActivity,SelectUserActivity::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }else if(page==1){
                    setAge?.visibility= View.GONE
                }
            }
        }
    }
}