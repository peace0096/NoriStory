package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.Login.LoginActivity
import java.io.FileOutputStream

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        val imageView = findViewById<ImageView>(R.id.image)
        imageView.setOnClickListener{
            initDB()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun initDB() {
        val dbfile = getDatabasePath("storydb.db")
        if(!dbfile.parentFile.exists())
            dbfile.parentFile.mkdir()
        if(!dbfile.exists()) {
            val file = resources.openRawResource(R.raw.storydb)
            val fileSize = file.available()
            val buffer = ByteArray(fileSize)
            file.read(buffer)
            file.close()
            dbfile.createNewFile()
            val output = FileOutputStream(dbfile)
            output.write(buffer)
            output.close()
            val dbhelp = DBHelper(this)
            dbhelp.firstDownload()
        }
    }
}