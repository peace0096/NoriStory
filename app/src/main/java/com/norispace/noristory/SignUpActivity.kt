package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.norispace.noristory.databinding.ActivitySignUpBinding
import com.norispace.noristory.MainMenu.MainActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    var page=1
    var id=""       //이름
    var age=0       //나이
    var character=0 //캐릭터 1 -> 남자/ 2-> 여자

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initButton()
    }

    private fun init(){
        binding.apply {
            frontBtn?.setOnClickListener {
                page++
                if(page==2){ //page == 2 -> 나이 , 캐릭터 선택
                    id=inputText?.text.toString()
                    inputText?.visibility=View.GONE
                    setAge?.visibility= View.VISIBLE
                    signupBoy?.setOnClickListener {
                        signupBoy.setImageResource(R.drawable.signup_boy_big)
                        signupGirl?.setImageResource(R.drawable.signup_girl_blur)
                        character=1
                    }
                    signupGirl?.setOnClickListener {
                        signupBoy?.setImageResource(R.drawable.signup_boy_blur)
                        signupGirl.setImageResource(R.drawable.signup_girl_big)
                        character=2
                    }
                }else if(page==3){ //로딩화면
                    saveData()
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
                    inputText?.visibility=View.VISIBLE
                }
            }
        }
    }

    private fun initButton(){

        binding.apply {
            val ageButton= arrayOf(signupAge3,signupAge4,signupAge5,signupAge6,signupAge7,signupAge8,signupAge9,signupAge10)
            for(i in 0 .. ageButton.size-1){
                ageButton[i]?.setOnClickListener {
                    age=i+3
                }
            }
        }
    }

    private fun saveData(){/////////////////////데이타 저장하기
        //id 이름
        //age 나이
        //character 1 -> 남자 , 2-> 여자 캐릭터
    }
}