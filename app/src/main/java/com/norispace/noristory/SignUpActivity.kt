package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.databinding.ActivitySignUpBinding
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    private lateinit var userViewModel:UserViewModel
    var page=1
    var id=""       //이름
    var birthday=""       //나이
    var character=0 //캐릭터 1 -> 남자/ 2-> 여자

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initButton()
    }

    private fun init(){
        initObserve()
        binding.apply {
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
            frontBtn?.setOnClickListener {
                page++
                if(page==2){ //page == 2 -> 나이 , 캐릭터 선택
                    id=inputText?.text.toString()
                    if(id == null) {
                        Toast.makeText(this@SignUpActivity, "닉네임을 입력해야합니다.", Toast.LENGTH_SHORT).show()
                        page--
                    }
                    else {
                        inputText?.visibility=View.GONE
                        setAge?.visibility= View.VISIBLE

                    }


                }else if(page==3){ //로딩화면
                    if(birthdayEditText?.text.toString() == "" || character == 0) {
                        Toast.makeText(baseContext, "캐릭터를 선택하고 생년월일을 입력하세요! (2000-01-01)", Toast.LENGTH_SHORT).show()
                        page--
                    }
                    else {
                        birthday = birthdayEditText?.text.toString()
                        loadingSecreen?.visibility=View.VISIBLE

                        val nick = inputText?.text.toString()
                        var gender:String? = null
                        if(character == 1) {
                            gender = "남"
                        }
                        else {
                            gender = "여"
                        }
                        userViewModel.login(nick, gender, birthday)


                    }


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

    }

    fun initObserve() {
        userViewModel = UserViewModel()

        userViewModel.tokenmodel.observe(this, Observer {
            if(it != "none" && it != null) {
                binding.apply {
                    loadingSecreen?.visibility=View.GONE
                }

                Log.i("login", "로그인되었습니다!")
                val intent= Intent(this@SignUpActivity,MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            else {
                Log.i("login", "실패!")
            }

        })
    }

    private fun saveData(){/////////////////////데이타 저장하기
        binding.apply {



        }
    }
}