package com.norispace.noristory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.norispace.noristory.databinding.ActivityBookMenuBinding

class BookMenuActivity : AppCompatActivity() {
    lateinit var binding : ActivityBookMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBookMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init()
    {
        binding.apply{
            backBtn?.setOnClickListener {
                val intent = Intent(this@BookMenuActivity, MyBooksActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            readBtn?.setOnClickListener{
                val intent = Intent(this@BookMenuActivity, ShowBookActivity::class.java)
                intent.putExtra("int", 1)
                startActivity(intent)
            }
            recordBtn?.setOnClickListener{
                val intent = Intent(this@BookMenuActivity, ShowBookActivity::class.java)
                intent.putExtra("int", 2)
                startActivity(intent)
            }
            shareBtn?.setOnClickListener{
                val alertDialog = AlertDialog.Builder(this@BookMenuActivity)
                    .setTitle("이야기 공유")
                    .setMessage("이야기를 도서관에 공유하시겠습니까?")
                    .setPositiveButton("공유"){dialog, which ->
                        Toast.makeText(applicationContext, "도서관에 공유되었습니다", Toast.LENGTH_SHORT).show()
                    }
                    .setNeutralButton("취소", null)
                    .create()
                alertDialog.show()
            }
            removeBtn?.setOnClickListener{
                val alertDialog = AlertDialog.Builder(this@BookMenuActivity)
                    .setTitle("이야기 삭제")
                    .setMessage("이야기를 삭제하시겠습니까?")
                    .setPositiveButton("삭제"){dialog, which ->
                        val intent = Intent(this@BookMenuActivity, EmptybookActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        Toast.makeText(applicationContext, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                    }
                    .setNeutralButton("취소", null)
                    .create()
                alertDialog.show()
            }
        }
    }
}