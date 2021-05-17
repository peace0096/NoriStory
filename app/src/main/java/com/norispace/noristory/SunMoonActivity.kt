package com.norispace.noristory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.norispace.noristory.databinding.ActivitySunMoonBinding


class SunMoonActivity : AppCompatActivity() {
    lateinit var binding : ActivitySunMoonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySunMoonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        var page =intent.getIntExtra("page",1)
        setImg(page)//페이지 배경 이미지 출력
        binding.gotoMainBtn?.visibility=View.GONE
        if(page==9) {//첫번째 선택 지점
            binding.nextButton?.visibility=View.GONE
            binding.choiceButton3?.visibility=View.GONE
            binding.choiceButton4?.visibility=View.GONE
            firstChoice(page)

        }else if(page==14){//두번째 선택 지점
            binding.choiceButton1?.visibility=View.GONE
            binding.choiceButton2?.visibility=View.GONE
            binding.nextButton?.visibility=View.GONE
            secondChoice(page)
        }
        else {//그 외 -> 선택 버튼 보이면 안됨
            binding.choiceButton1?.visibility=View.GONE
            binding.choiceButton2?.visibility=View.GONE
            binding.choiceButton3?.visibility=View.GONE
            binding.choiceButton4?.visibility=View.GONE

        }
            binding.apply {


                exitButton?.setOnClickListener {//이야기 나가기 버튼
                    val intent = Intent(this@SunMoonActivity, BooksActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                nextButton?.setOnClickListener {//다음페이지 버튼

                    if(page==21 || page==24||page==29){//21, 24, 29페이지 에서 다음 페이지 버튼 누를 경우
                        binding.nextButton?.visibility=View.GONE
                        binding.postButton?.visibility=View.GONE
                        binding.exitButton?.visibility=View.GONE
                        end() // 이야기 끝났음을 알리는 페이지 출력 (sunmoonend)
                    }else{//그 외 -> 페이지 1 증가
                        val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                       page += 1
                        intent.putExtra("page", page)
                        startActivity(intent)
                    }

                }
                if (page == 1) {//첫번째 페이지에서는 이전페이지 버튼 없음
                    postButton?.visibility = View.GONE

                } else {
                    postButton?.setOnClickListener {
                        if(page!=1) {
                            val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                            if(page==25){//25페이지(피그마 참고)에서 이전 페이지 버튼 누르면 첫번째 선택 페이지로 이동
                                page=9
                            }else if(page==22){//22페이지(피그마 참고)에서 이전 페이지 버튼 누르면 두번째 선택 페이지로 이동
                                page=14
                            }else{//그 외 -> 페이지 1 감소
                                page -= 1
                            }

                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.putExtra("page", page)
                            startActivity(intent)
                        }
                    }
                }

            }
    }
    private fun end(){//이야기가 끝났음을 알리고, 메인메뉴로 돌아가는 버튼 출력함

        val imgName = "sunmoonend"
        val id = resources.getIdentifier(imgName, "drawable", packageName)
        binding.imageView?.setImageResource(id)
        binding.gotoMainBtn?.visibility=View.VISIBLE
        binding.gotoMainBtn?.setOnClickListener {
            val intent =Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun setImg(page:Int){//배경 이미지 출력
        binding.apply{
                val imgName = "sunmoon" + page.toString()
                val id = resources.getIdentifier(imgName, "drawable", packageName)
                imageView?.setImageResource(id)
        }
    }

    private  fun firstChoice(page:Int){
        binding.apply {
            choiceButton1?.visibility = View.VISIBLE//첫번째 분기점 -> 선택버튼 1, 2
            choiceButton2?.visibility = View.VISIBLE
            choiceButton1?.setOnClickListener {//누르는 버튼에 따라 그에 맞는 페이지로 이동함(피그마 참고)
                val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                intent.putExtra("page", 10)
                //intent.putExtra("choice",1)
                startActivity(intent)
            }
            choiceButton2?.setOnClickListener {
                val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                intent.putExtra("page", 25)
                //intent.putExtra("choice",2)
                startActivity(intent)
            }

        }
    }
    private  fun secondChoice(page:Int){
        binding.apply {
            choiceButton3?.visibility=View.VISIBLE//두번째 분기점 -> 선택버튼 3, 4
            choiceButton4?.visibility=View.VISIBLE
            choiceButton3?.setOnClickListener {//누르는 버튼에 따라 그에 맞는 페이지로 이동함(피그마 참고)
                val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                intent.putExtra("page", 15)
                //intent.putExtra("choice",1)
                startActivity(intent)
            }
            choiceButton4?.setOnClickListener {
                val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                intent.putExtra("page", 22)
                //intent.putExtra("choice",2)
                startActivity(intent)
            }
        }
    }
}