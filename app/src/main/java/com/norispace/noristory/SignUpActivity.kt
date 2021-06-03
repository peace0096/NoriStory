package com.norispace.noristory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.databinding.ActivitySignUpBinding

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
                if(page==1){ //page == 1 -> id입력
                    showText?.text="비밀번호는 뭐로할까요?"
                    inputText?.text?.clear()
                }else if(page==2){ //page == 2 -> pw입력
                    showText?.text="한번 더 입력해주세요!"
                    inputText?.text?.clear()
                }else if(page==3){ //page == 3 -> pw재입력

                }else if(page==4){ //page == 4 -> 나이, 성별 선택

                }
            }
        }
    }
}