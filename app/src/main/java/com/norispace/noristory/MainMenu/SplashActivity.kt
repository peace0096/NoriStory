package com.norispace.noristory.MainMenu

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.Login.LoginActivity
import com.norispace.noristory.R
import java.io.FileOutputStream

class SplashActivity : AppCompatActivity() {
    var flag = false
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
            if(flag) {
                startActivity(intent)
                finish()
            }
        }
    }


    fun initDB() {
        val dbfile = getDatabasePath("storydb.db")
        if(!dbfile.parentFile.exists())
            dbfile.parentFile.mkdir()
        if(!dbfile.exists()) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("처음이시군요! 원활한 실행을 위해 데이터를 다운받아야합니다.\n(주의 : 데이터환경이 필요)")
            builder.setNegativeButton("취소", DialogInterface.OnClickListener{ dialogInterface: DialogInterface, i: Int ->
                finish()
            })

            builder.setPositiveButton("시작", DialogInterface.OnClickListener{ dialogInterface: DialogInterface, i: Int ->
                val loadImage = findViewById<ImageView>(R.id.loadingSecreen)
                loadImage.visibility = View.VISIBLE
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
            })
            builder.show()

        }else {
            flag = true
        }

    }
}