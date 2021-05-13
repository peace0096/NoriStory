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
        var choice =intent.getIntExtra("choice",0)
        setImg(page,choice)
        if(page==9) {
            binding.nextButton?.visibility=View.GONE
            firstChoice(page)

        }
        else {
            binding.choiceButton1?.visibility=View.GONE
            binding.choiceButton2?.visibility=View.GONE
        }
            binding.apply {


                exitButton?.setOnClickListener {
                    val intent = Intent(this@SunMoonActivity, BooksActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                nextButton?.setOnClickListener {
                    val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                    page += 1
                    intent.putExtra("page", page)
                    intent.putExtra("choice",choice)
                    startActivity(intent)
                }
                if (page == 1) {
                    postButton?.visibility = View.GONE

                } else {
                    postButton?.setOnClickListener {
                        if(page!=1) {
                            val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                            page -= 1
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.putExtra("page", page)
                            intent.putExtra("choice", choice)
                            startActivity(intent)
                        }
                    }
                }

            }


    }

    private fun setImg(page:Int,choice:Int){
        //10페이지가 첫 번째 분기점
        if(page>=10){
            binding.apply{
                val imgName = "sunmoon" + page.toString() + "_"+choice.toString()
                val id = resources.getIdentifier(imgName, "drawable", packageName)
                imageView?.setImageResource(id)
            }
        }else {
            binding.apply {
                val imgName = "sunmoon" + page.toString()
                val id = resources.getIdentifier(imgName, "drawable", packageName)
                imageView?.setImageResource(id)
            }
        }
    }

    private  fun firstChoice(page:Int){
        binding.apply {
            choiceButton1?.visibility = View.VISIBLE
            choiceButton2?.visibility = View.VISIBLE
            choiceButton1?.setOnClickListener {
                val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                intent.putExtra("page", page+1)
                intent.putExtra("choice",1)
                startActivity(intent)
            }
            choiceButton2?.setOnClickListener {
                val intent = Intent(this@SunMoonActivity, SunMoonActivity::class.java)
                intent.putExtra("page", page+1)
                intent.putExtra("choice",2)
                startActivity(intent)
            }

//            val height=imageView?.height
//            val width=imageView?.width

//            var x:Int=((8/10)*width!!)
//            var y:Int=((2/10)*height!!)
////            val params: FrameLayout.LayoutParams=FrameLayout.LayoutParams(width,height)
////            params.setMargins((8/10)*width!!,10,10,10)
//            val param =choiceButton1?.layoutParams as LinearLayout.LayoutParams
//            param.setMargins(620,100,0,0)
//            choiceButton1?.layoutParams=param
        }
    }
}