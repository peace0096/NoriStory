package com.norispace.noristory.MakeStory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.R
import com.norispace.noristory.StoryMenuActivity
import com.norispace.noristory.databinding.ActivitySelectSubjectBinding
import kotlinx.android.synthetic.main.activity_select_subject.*

class SelectSubjectActivity : AppCompatActivity() {
    lateinit var binding:ActivitySelectSubjectBinding
    var flag=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySelectSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.apply {
            subjectAppleSmall?.setOnClickListener {
                deleteSmallImg()
                subjectAppleBig?.visibility= View.VISIBLE
                subjectBearBlur?.visibility= View.VISIBLE
                subjectHerbBlur?.visibility= View.VISIBLE
                subjectDragonBlur?.visibility= View.VISIBLE
                theme?.text="독사과"
                subject_select_complete?.setImageResource(R.drawable.subject_select_active)
                flag=1
            }
            subjectBearSmall?.setOnClickListener {
                deleteSmallImg()
                subjectBearBig?.visibility= View.VISIBLE
                subjectAppleBlur?.visibility= View.VISIBLE
                subjectHerbBlur?.visibility= View.VISIBLE
                subjectDragonBlur?.visibility= View.VISIBLE
                theme?.text="화가 잔뜩난 불곰의 등장"
                subject_select_complete?.setImageResource(R.drawable.subject_select_active)
                flag=1
            }
            subjectHerbSmall?.setOnClickListener {
                deleteSmallImg()
                subjectHerbBig?.visibility= View.VISIBLE
                subjectAppleBlur?.visibility= View.VISIBLE
                subjectBearBlur?.visibility= View.VISIBLE
                subjectDragonBlur?.visibility= View.VISIBLE
                theme?.text="마법의 약초"
                subject_select_complete?.setImageResource(R.drawable.subject_select_active)
                flag=1
            }
            subjectDragonSmall?.setOnClickListener {
                deleteSmallImg()
                subjectDragonBig?.visibility= View.VISIBLE
                subjectAppleBlur?.visibility= View.VISIBLE
                subjectBearBlur?.visibility= View.VISIBLE
                subjectHerbBlur?.visibility= View.VISIBLE
                theme?.text="머리 세개달린 괴물"
                subject_select_complete?.setImageResource(R.drawable.subject_select_active)
                flag=1
            }
            subjectAppleBlur?.setOnClickListener {
                theme?.text="독사과"
                deleteBigImg()
                setBlurImg()
                subjectAppleBlur?.visibility=View.GONE
                subjectAppleBig?.visibility= View.VISIBLE
            }
            subjectBearBlur?.setOnClickListener {
                theme?.text="화가 잔뜩난 불곰의 등장"
                deleteBigImg()
                setBlurImg()
                subjectBearBlur?.visibility=View.GONE
                subjectBearBig?.visibility= View.VISIBLE
            }
            subjectHerbBlur?.setOnClickListener {
                theme?.text="마법의 약초"
                deleteBigImg()
                setBlurImg()
                subjectHerbBlur?.visibility=View.GONE
                subjectHerbBig?.visibility= View.VISIBLE
            }
            subjectDragonBlur?.setOnClickListener {
                theme?.text="머리 세개달린 괴물"
                deleteBigImg()
                setBlurImg()
                subjectDragonBlur?.visibility=View.GONE
                subjectDragonBig?.visibility= View.VISIBLE
            }
            subject_select_complete?.setOnClickListener {
                if(flag==1){
                    val intent= Intent(this@SelectSubjectActivity,
                        SelectCharacterActivity::class.java)
                    startActivity(intent)
                }
            }
            mybookBack?.setOnClickListener {
                val intent= Intent(this@SelectSubjectActivity,
                    MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            mybookHome?.setOnClickListener {
                finish()
            }
        }
    }

    private fun setBlurImg(){
        binding.apply {
            subjectAppleBlur?.visibility=View.VISIBLE
            subjectBearBlur?.visibility=View.VISIBLE
            subjectHerbBlur?.visibility=View.VISIBLE
            subjectDragonBlur?.visibility=View.VISIBLE
        }
    }

    private fun deleteBigImg(){
        binding.apply {
            subjectAppleBig?.visibility=View.GONE
            subjectBearBig?.visibility=View.GONE
            subjectHerbBig?.visibility=View.GONE
            subjectDragonBig?.visibility=View.GONE
        }
    }

    private fun deleteSmallImg(){
        binding.apply {
            subjectTheme?.visibility=View.VISIBLE
            theme?.visibility=View.VISIBLE
            subjectAppleSmall?.visibility=View.GONE
            subjectBearSmall?.visibility=View.GONE
            subjectHerbSmall?.visibility=View.GONE
            subjectDragonSmall?.visibility=View.GONE
        }
    }
}