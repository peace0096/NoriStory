package com.norispace.noristory.OptionalStory

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.norispace.noristory.MainActivity
import com.norispace.noristory.databinding.ActivitySunMoonBinding
import com.norispace.noristory.Model.OptionalStory_Model
import com.norispace.service.S3Helper
import java.net.URL


class SunMoonActivity : AppCompatActivity() {
    val s3Helper = S3Helper(this)
    lateinit var binding : ActivitySunMoonBinding
    val page = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySunMoonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()

    }


    private fun initData() {
        page.value = 1

        page.observe(this, Observer {
            refresh(it.toInt())
        })

        OptionalViewModel.getInstance().story.observe(this, Observer {
            if(it != null) {
                init()
            }
        })

        val title = intent.getStringExtra("title")
        if(title != null)
            OptionalViewModel.getInstance().getOptionalStory(title)
    }

    private fun init(){
        binding.apply {
            refresh(1)

            binding.gotoMainBtn?.visibility=View.GONE
            exitButton?.setOnClickListener {//이야기 나가기 버튼
                val intent = Intent(this@SunMoonActivity, OptionalActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            postButton?.setOnClickListener {

                if(page.value != null) {
                    page.value = page.value!! - 1
                }
            }
            binding.gotoMainBtn?.setOnClickListener {
                val intent = Intent(this@SunMoonActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

        }
    }

    private fun refresh(page:Int) {
        val list = OptionalViewModel.getInstance().story.value
        if (list != null) {
            for(e in list) {
                if(e.page == page) {
                    val OptionalStoryResponse = e

                    refreshImg(OptionalStoryResponse)
                    refreshListener(OptionalStoryResponse)
                    refreshWidget(OptionalStoryResponse)
                }

            }
        }
    }

    private fun refreshImg(e: OptionalStory_Model) {
        val thread = Thread {

            val url = s3Helper.getImage(e.image.toString())
            Log.d("url", url.toString())

            val connection = URL(url.toString()).openConnection()
            connection.doInput = true
            connection.connect()
            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            Thread.sleep(1000)
            runOnUiThread {
                binding.apply {
                    if (imageView != null) {
                        imageView.setImageDrawable(BitmapDrawable(bitmap))
                    }
                }
            }
        }
        thread.start()
    }

    private fun refreshListener(e: OptionalStory_Model) {
        binding.apply {
            choiceButton1?.setOnClickListener {//누르는 버튼에 따라 그에 맞는 페이지로 이동함(피그마 참고)
                if(e.nextPage1 != null)
                    page.value = e.nextPage1
            }
            choiceButton2?.setOnClickListener {
                if(e.nextPage2 != null)
                    page.value = e.nextPage2
            }

            nextButton?.setOnClickListener {//다음페이지 버튼

                if (page.value != null) {
                    page.value = page.value!! + 1
                }
                if(e.nextPage1 == 0 && e.nextPage1 == 0) {
                    page.value = 0
                }
            }


        }
    }

    private fun refreshWidget(e: OptionalStory_Model) {
        binding.apply {

            postButton?.visibility = View.VISIBLE
            if(e.nextPage1 != 0 && e.nextPage2 != 0) {//선택지페이지
                val thread = Thread {
                    Log.d("in 1 page", "${e.nextPage2 != 0}, ${e.nextPage2.toString()}")
                    val url1 = s3Helper.getImage(e.nextImage1.toString())
                    val url2 = s3Helper.getImage(e.nextImage2.toString())
                    val connection1 = URL(url1.toString()).openConnection()
                    val connection2 = URL(url2.toString()).openConnection()
                    connection1.doInput = true
                    connection2.doInput = true
                    connection1.connect()
                    connection2.connect()
                    val inputStream1 = connection1.getInputStream()
                    val inputStream2 = connection2.getInputStream()
                    val bitmap1 = BitmapFactory.decodeStream(inputStream1)
                    val bitmap2 = BitmapFactory.decodeStream(inputStream2)

                    runOnUiThread{

                        choiceButton1?.setImageDrawable(BitmapDrawable(bitmap1))
                        choiceButton2?.setImageDrawable(BitmapDrawable(bitmap2))
                        choiceButton1?.visibility = View.VISIBLE
                        choiceButton2?.visibility = View.VISIBLE
                        exitButton?.visibility = View.VISIBLE
                        nextButton?.visibility = View.GONE

                    }
                }
                thread.start()


            }
            else if(e.nextPage1 == 0 && e.nextPage2 == 0 && e.page == 0) { //마지막 페이지
                exitButton?.visibility = View.GONE
                nextButton?.visibility = View.GONE
                choiceButton1?.visibility = View.GONE
                choiceButton2?.visibility = View.GONE
                gotoMainBtn?.visibility = View.VISIBLE
            }
            else {  //다음버튼만 있는 페이지
                Log.d("here", "here")
                choiceButton1?.visibility = View.GONE
                choiceButton2?.visibility = View.GONE
                exitButton?.visibility = View.VISIBLE
                nextButton?.visibility = View.VISIBLE
            }

            if(page.value == 1 || page.value == 0) {
                postButton?.visibility = View.GONE

            }

        }

    }


    private fun end(){//이야기가 끝났음을 알리고, 메인메뉴로 돌아가는 버튼 출력함

        val imgName = "sunmoonend"
        val id = resources.getIdentifier(imgName, "drawable", packageName)
        binding.imageView?.setImageResource(id)


    }
}