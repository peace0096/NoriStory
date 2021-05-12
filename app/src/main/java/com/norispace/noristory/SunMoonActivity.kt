package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.norispace.noristory.databinding.ActivitySunMoon1Binding
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
        setImg(page)
        binding.apply {
            exitButton?.setOnClickListener {
                val intent= Intent(this@SunMoonActivity,BooksActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            nextButton?.setOnClickListener {
                val intent= Intent(this@SunMoonActivity,SunMoonActivity::class.java)
                page+=1
                intent.putExtra("page",page)
                startActivity(intent)
            }
            if(page==1){
                postButton?.visibility= View.GONE
            }
            else{
                postButton?.setOnClickListener {
                    val intent= Intent(this@SunMoonActivity,SunMoonActivity::class.java)
                    page-=1
                    intent.putExtra("page",page)
                    startActivity(intent)
                }
            }

        }

    }

    private fun setImg(page:Int){
        binding.apply{
            val imgName="sunmoon"+page.toString()
            val id = resources.getIdentifier(imgName, "drawable", packageName)
            imageView?.setImageResource(id)
        }
    }
}