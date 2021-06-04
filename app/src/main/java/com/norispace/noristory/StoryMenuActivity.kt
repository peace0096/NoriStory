package com.norispace.noristory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.norispace.noristory.MakeStory.SelectSubjectActivity
import com.norispace.noristory.OptionalStory.OptionalActivity
import com.norispace.noristory.databinding.ActivityStoryMenuBinding
import kotlinx.android.synthetic.main.activity_story_menu.*

class StoryMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoryMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStoryMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init()
    {
        binding.apply {
            optional_btn.setOnClickListener {
                val intent = Intent(this@StoryMenuActivity, OptionalActivity::class.java)
                startActivity(intent)
            }

            subject_btn.setOnClickListener {
                val intent = Intent(this@StoryMenuActivity, SelectSubjectActivity::class.java)
                startActivity(intent)
            }

            home_btn.setOnClickListener {
                val intent = Intent(this@StoryMenuActivity, MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }

    }
}