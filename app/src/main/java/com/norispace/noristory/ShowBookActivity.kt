package com.norispace.noristory

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.norispace.noristory.databinding.ActivityShowBookBinding
import com.norispace.noristory.databinding.ActivitySunMoonBinding
import kotlinx.android.synthetic.main.activity_show_book.*
import java.io.IOException

class ShowBookActivity : AppCompatActivity() {
    var imagenum = 1
    lateinit var binding : ActivityShowBookBinding
    var isrecord = false
    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var state: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initimage()
        init()
    }


    fun initimage()
    {

        
        if(imagenum == 1) {
            postbtn.visibility = View.INVISIBLE
            storyView.setImageResource(R.drawable.sunmoon1)
        }
        else if(imagenum == 2) {
            postbtn.visibility = View.VISIBLE
            storyView.setImageResource(R.drawable.sunmoon2)
        }
        else if(imagenum == 3)
            storyView.setImageResource(R.drawable.sunmoon3)
        else if(imagenum == 4)
            storyView.setImageResource(R.drawable.sunmoon4)


    }

    fun init()
    {
        val i = intent
        val mode = i.getIntExtra("int", -1)
        if(mode == 1)
        {
            Titletext.visibility = View.VISIBLE
            recordbutton.visibility = View.INVISIBLE
            playbtn.visibility = View.INVISIBLE
            pausebtn.visibility = View.INVISIBLE
            stopbtn.visibility = View.INVISIBLE
        }
        else if(mode == 2)
        {
            Titletext.visibility = View.INVISIBLE
            recordbutton.visibility = View.VISIBLE
            playbtn.visibility = View.VISIBLE
            pausebtn.visibility = View.VISIBLE
            stopbtn?.visibility = View.VISIBLE
        }

        binding.apply {




            postbtn?.setOnClickListener{
                recordbutton?.text = "??????"
                isrecord = false

                if(imagenum > 1)
                    imagenum -= 1
                initimage()
            }
            nextbtn?.setOnClickListener{
                recordbutton?.text = "??????"
                isrecord = false

                if(imagenum < 4)
                    imagenum += 1
                initimage()
            }
            exitbtn?.setOnClickListener{
                val intent = Intent(this@ShowBookActivity, BookMenuActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            recordbutton?.setOnClickListener{
                if(!isrecord) {
                    if (ContextCompat.checkSelfPermission(this@ShowBookActivity,
                            android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this@ShowBookActivity,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //Permission is not granted
                        val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        ActivityCompat.requestPermissions(this@ShowBookActivity, permissions,0)
                    } else {
                        recordbutton.text = "??????"
                        isrecord = true
                        startRecording()
                    }
                }
                else
                {
                    stopRecording()
                    recordbutton.text = "??????"
                    isrecord = false
                }
            }
            playbtn?.setOnClickListener{
                playRecord()
            }
        }
    }

    fun startRecording()
    {
        val fileName: String = "ex.Title"+ imagenum +".mp3"
        output = Environment.getExternalStorageDirectory().absolutePath + "/Download/"+ fileName //??????????????? ?????? ??????
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource((MediaRecorder.AudioSource.MIC))
        mediaRecorder?.setOutputFormat((MediaRecorder.OutputFormat.MPEG_4))
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)

        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "?????? ?????????????????????.", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException){
            e.printStackTrace()
        } catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun stopRecording()
    {
        if(state){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
            Toast.makeText(this, "?????? ???????????????.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "????????? ????????? ????????????.", Toast.LENGTH_SHORT).show()
        }
    }

    fun playRecord()
    {
        mediaPlayer =  MediaPlayer()
        mediaPlayer?.setDataSource(output);
        mediaRecorder?.prepare();
        mediaRecorder?.start();
    }
}