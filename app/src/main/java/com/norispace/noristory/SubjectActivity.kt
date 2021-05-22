package com.norispace.noristory

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import com.norispace.noristory.databinding.ActivityMainBinding
import com.norispace.noristory.databinding.ActivitySubjectBinding
import kotlinx.android.synthetic.main.activity_subject.*
import kotlin.math.sqrt

class SubjectActivity : AppCompatActivity() {
    private var images: ArrayList<ImageView> = ArrayList()
    private var texts: ArrayList<TextView> = ArrayList()

    lateinit var binding: ActivitySubjectBinding
    lateinit var contentData: ArrayList<SubjectStoryData>
    private var lastTouchTag = ""
    private val sliceSize = 8
    private var xCoordiante = Array(sliceSize, { 0.0f })
    private var yCoordiante = Array(sliceSize, { 0.0f })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initImgSource()
        init()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        binding.apply {
            for (i in 1 until sliceSize) {
                for (j in 1..i) {
                    yCoordiante[i] = yCoordiante[i] + (relativeLayout?.height?.div(sliceSize) ?: 0)
                    xCoordiante[i] = xCoordiante[i] + (relativeLayout?.width?.div(sliceSize) ?: 0)
                }
            }
        }
    }

    private fun initImgSource() {

    }

    private fun initGrid() {
        binding.apply {
            var width = 0
            var height = 0
            val vto: ViewTreeObserver? = relativeLayout?.viewTreeObserver
            relativeLayout?.viewTreeObserver?.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {

                    width = relativeLayout.measuredWidth
                    height = relativeLayout.measuredHeight
                    relativeLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
//            vto.addOnPreDrawListener(object: ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw() :Boolean{
//
//                    height = relativeLayout.measuredHeight
//                    width = relativeLayout.measuredWidth
//                    relativeLayout.viewTreeObserver.removeOnPreDrawListener(this);
//                    return true
//                }
//            })
        }
    }

    private fun init() {
        contentData = ArrayList()
        var imageCount = 0
        var textCount = 0
        val basicHeight = 400
        val basicWidth = 400
        binding.apply {
            imageView?.setOnClickListener {

                val imageView = ImageView(this@SubjectActivity)
                imageView.layoutParams = LinearLayout.LayoutParams(basicWidth, basicHeight)
                //imageView.x = 20F //setting margin from left
                //imageView.y = 20F //setting margin from top
                imageView.tag = "image" + imageCount.toString()
                images.add(imageView)// Add ImageView to LinearLayout
                imageCount++
                imageView.setImageResource(R.drawable.ic_android_black_24dp)

                relativeLayout?.addView(imageView) //adding image to the layout

                if (!texts.isEmpty()) { //텍스트를 그림보다 앞에 나오게
                    for (i in 0 until textCount) {
                        texts[i].bringToFront()
                    }
                }

                var xDelta = 0
                var yDelta = 0
                var locationX = 0
                var locationY = 0
                imageView.setOnTouchListener(View.OnTouchListener { v, event ->
                    lastTouchTag = imageView.tag.toString()
                    val x = event.rawX.toInt()
                    val y = event.rawY.toInt()
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            val lParams = v.layoutParams as RelativeLayout.LayoutParams
                            xDelta = x - lParams.leftMargin
                            yDelta = y - lParams.topMargin

                        }
//                        MotionEvent.ACTION_UP -> {
//                            val lParams = v.getLayoutParams() as RelativeLayout.LayoutParams
//                            xDelta = x - lParams.leftMargin
//                            yDelta = y - lParams.topMargin
//                        }
                        MotionEvent.ACTION_MOVE -> {
                            val layoutParams = v.layoutParams as RelativeLayout.LayoutParams
                            //layoutParams.leftMargin = x - xDelta
                            //layoutParams.topMargin = y - yDelta
                            //layoutParams.rightMargin = 0
                            //layoutParams.bottomMargin = 0
                            var min = 10000.0f
//                            var tempX=layoutParams.leftMargin
//                            var tempY=layoutParams.leftMargin
                            var tempX = 0.0f
                            var tempY = 0.0f
                            for (i in 0 until sliceSize) {
                                for (j in 0 until sliceSize) {
                                    val temp =
                                        sqrt((yCoordiante[i] - y + yDelta) * (yCoordiante[i] - y + yDelta) + (xCoordiante[j] - x) * (xCoordiante[j] - x))
                                    if (min > temp) {
                                        min = temp
                                        tempX = xCoordiante[j]
                                        tempY = yCoordiante[i]
                                        locationX=j
                                        locationY=i
                                    }
                                }
                            }

                            //layoutParams.leftMargin = Xtemp
                            //layoutParams.topMargin = Ytemp
                            imageView.x = tempX
                            imageView.y = tempY

                            //Toast.makeText(this@SubjectActivity, yDelta.toString() + "  " + y.toString() + " " + tempY.toString(), Toast.LENGTH_SHORT).show()
                            v.layoutParams = layoutParams
                        }
                    }
                    relativeLayout?.invalidate()
                    true
                })
                val tempName = "imageName"
                contentData.add(
                    SubjectStoryData(
                        0,
                        imageView.width,
                        imageView.height,
                        locationX,
                        locationY,
                        1,
                        tempName
                    )
                )
            }

            imageView2?.setOnClickListener {
                val imageView = ImageView(this@SubjectActivity)
                imageView.layoutParams = LinearLayout.LayoutParams(basicWidth, basicHeight)
                imageView.tag = "image" + imageCount.toString()
                images.add(imageView)
                imageCount++
                imageView.setImageResource(R.drawable.ic_baseline_chat_bubble_24)

                relativeLayout?.addView(imageView) //adding image to the layout
                if (!texts.isEmpty()) {
                    for (i in 0 until textCount) {
                        texts[i].bringToFront()
                    }
                }
                var xDelta = 0
                var yDelta = 0
                var locationX = 0
                var locationY = 0
                imageView.setOnTouchListener(View.OnTouchListener { v, event ->
                    lastTouchTag = imageView.tag.toString()
                    val x = event.rawX.toInt()
                    val y = event.rawY.toInt()
                    when (event.getAction() and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_DOWN -> {
                            val lParams = v.layoutParams as RelativeLayout.LayoutParams
                            xDelta = x - lParams.leftMargin
                            yDelta = y - lParams.topMargin

                        }
                        MotionEvent.ACTION_MOVE -> {
                            val layoutParams = v.layoutParams as RelativeLayout.LayoutParams

                            var min = 10000.0f
                            var Xtemp = 0.0f
                            var Ytemp = 0.0f
                            for (i in 0 until sliceSize) {
                                for (j in 0 until sliceSize) {
                                    val temp =
                                        sqrt((yCoordiante[i] - y + yDelta) * (yCoordiante[i] - y + yDelta) + (xCoordiante[j] - x) * (xCoordiante[j] - x))
                                    if (min > temp) {
                                        min = temp
                                        Xtemp = xCoordiante[j]
                                        Ytemp = yCoordiante[i]
                                        locationX = j
                                        locationY = i
                                    }
                                }
                            }
                            imageView.x = Xtemp
                            imageView.y = Ytemp

                            Toast.makeText(
                                this@SubjectActivity,
                                yDelta.toString() + "  " + y.toString() + " " + Ytemp.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            v.layoutParams = layoutParams
                        }
                    }
                    relativeLayout?.invalidate()
                    true
                })
                val tempName = "imageName"
                contentData.add(
                    SubjectStoryData(
                        0,
                        imageView2.width,
                        imageView2.height,
                        locationX,
                        locationY,
                        1,
                        tempName
                    )
                )
            }


        }
        textInputBtn?.setOnClickListener {
            val text = editText?.text.toString()
            if (text.replace(" ", "") == "") {
                Toast.makeText(this@SubjectActivity, "줄거리를 입력하세요!", Toast.LENGTH_SHORT).show()
            } else {
                val textView = TextView(this@SubjectActivity)
                textView.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textView.textSize = 14.0f
                textView.tag = "text" + textCount.toString()
                textView.text = text
                textView.setTextColor(Color.BLACK)
                texts.add(textView)
                textCount++
                relativeLayout?.addView(textView)


                editText?.setText("")
                var xDelta = 0
                var yDelta = 0
                var locationX = 0
                var locationY = 0
                textView.setOnTouchListener(View.OnTouchListener { v, event ->
                    lastTouchTag = textView.tag.toString()
                    val x = event.rawX.toInt()
                    val y = event.rawY.toInt()
                    when (event.getAction() and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_DOWN -> {
                            val lParams = v.layoutParams as RelativeLayout.LayoutParams
                            xDelta = x - lParams.leftMargin
                            yDelta = y - lParams.topMargin

                        }
                        MotionEvent.ACTION_MOVE -> {
                            val layoutParams = v.layoutParams as RelativeLayout.LayoutParams
                            var min = 10000.0f
                            var Xtemp = 0.0f
                            var Ytemp = 0.0f
                            for (i in 0 until sliceSize) {
                                for (j in 0 until sliceSize) {
                                    val temp =
                                        sqrt((yCoordiante[i] - y + yDelta) * (yCoordiante[i] - y + yDelta) + (xCoordiante[j] - x) * (xCoordiante[j] - x))
                                    if (min > temp) {
                                        min = temp
                                        Xtemp = xCoordiante[j]
                                        Ytemp = yCoordiante[i]
                                        locationX = j
                                        locationY = i
                                    }
                                }
                            }
                            textView.x = Xtemp
                            textView.y = Ytemp

                            v.layoutParams = layoutParams
                        }
                    }
                    relativeLayout?.invalidate()
                    true
                })
                //텍스트 정보 저장
                contentData.add(
                    SubjectStoryData(
                        0,
                        textView.textSize.toInt(),
                        0,
                        locationX,
                        locationY,
                        2,
                        textView.text.toString()
                    )
                )

            }
        }



        deleteBtn?.setOnClickListener {
//                for (i in 0 until imageCount) {
//                    if (images[i].tag == lastTouchTag) {
//                        images[i].setImageResource(0)
//                        //image 메모리에서 삭제 ..어떻게..
//                    }
//                }
//                for (i in 0 until textCount) {
//                    if (texts[i].tag == lastTouchTag) {
//                        relativeLayout?.getChild
//                        texts[i].re
//                        //image 메모리에서 삭제 ..어떻게..
//                    }
//                }
            for (i in 0 until relativeLayout?.childCount!!) {
                if (relativeLayout.getChildAt(i)?.tag == lastTouchTag) {
                    relativeLayout.removeViewAt(i)
                    contentData.removeAt(i)
                    break
                }
            }
        }

        biggerBtn?.setOnClickListener {
//            for (i in 0 until imageCount) {
//                if (images[i].tag == lastTouchTag) {
//                    val tempHeight = images[i].layoutParams.height * 2
//                    val tempWidth = images[i].layoutParams.width * 2
//                    //images[i].layoutParams=LinearLayout.LayoutParams(tempWidth, tempHeight)
//                    images[i].layoutParams.height = tempHeight
//                    images[i].layoutParams.width = tempWidth
//                    images[i].requestLayout()
//                }
//            }
            for (i in 0 until relativeLayout?.childCount!!) {
                if (relativeLayout.getChildAt(i)?.tag == lastTouchTag) {
                    if(relativeLayout.getChildAt(i) is ImageView) {
                        relativeLayout.getChildAt(i).layoutParams.height =
                            (relativeLayout.getChildAt(i).layoutParams.height * 1.5).toInt()
                        relativeLayout.getChildAt(i).layoutParams.width =
                            (relativeLayout.getChildAt(i).layoutParams.width * 1.5).toInt()
                        contentData[i].sizeX=relativeLayout.getChildAt(i).layoutParams.width
                        contentData[i].sizeY=relativeLayout.getChildAt(i).layoutParams.height
                    }
                    else if(relativeLayout.getChildAt(i) is TextView){
                        Toast.makeText(this, (relativeLayout.getChildAt(i) as TextView).text.toString(),Toast.LENGTH_SHORT).show()
                        (relativeLayout.getChildAt(i) as TextView).textSize*=1.5f
                        contentData[i].sizeX= (relativeLayout.getChildAt(i) as TextView).textSize.toInt()
                    }

                    break
                }
                relativeLayout.getChildAt(i).requestLayout()
            }
        }

        smallerBtn?.setOnClickListener {
//            for (i in 0 until imageCount) {
//                if (images[i].tag == lastTouchTag) {
//                    images[i].layoutParams.height = images[i].layoutParams.height / 2
//                    images[i].layoutParams.width = images[i].layoutParams.width / 2
//                    images[i].requestLayout()
//                }
//            }
            for (i in 0 until relativeLayout?.childCount!!) {
                if (relativeLayout.getChildAt(i)?.tag == lastTouchTag) {
                    if(relativeLayout.getChildAt(i) is ImageView) {
                        relativeLayout.getChildAt(i).layoutParams.height =
                            (relativeLayout.getChildAt(i).layoutParams.height / 1.5).toInt()
                        relativeLayout.getChildAt(i).layoutParams.width =
                            (relativeLayout.getChildAt(i).layoutParams.width / 1.5).toInt()
                        contentData[i].sizeX=relativeLayout.getChildAt(i).layoutParams.width
                        contentData[i].sizeY=relativeLayout.getChildAt(i).layoutParams.height
                    }
                    else if(relativeLayout.getChildAt(i) is TextView){
                        Toast.makeText(this, (relativeLayout.getChildAt(i) as TextView).text.toString(),Toast.LENGTH_SHORT).show()
                        (relativeLayout.getChildAt(i) as TextView).textSize/=1.5f
                        contentData[i].sizeX= (relativeLayout.getChildAt(i) as TextView).textSize.toInt()
                    }
                    break
                }
                relativeLayout.getChildAt(i).requestLayout()
            }
        }
    }


//    private fun initDrag(imageCount:Int,textCount:Int){
//        val start=imageCount+textCount-1
//        var xDelta = 0
//        var yDelta = 0
//        binding.apply {
//            for(i in start until relativeLayout.childCount){
//                relativeLayout.getChildAt(i).setOnTouchListener(View.OnTouchListener { v, event ->
//                    lastTouchTag = relativeLayout.getChildAt(i).tag.toString()
//                    val x = event.rawX.toInt()
//                    val y = event.rawY.toInt()
//                    when (event.getAction() and MotionEvent.ACTION_MASK) {
//                        MotionEvent.ACTION_DOWN -> {
//                            val lParams = v.getLayoutParams() as RelativeLayout.LayoutParams
//                            xDelta = x - lParams.leftMargin
//                            yDelta = y - lParams.topMargin
//
//                        }
////                        MotionEvent.ACTION_UP -> {
////                            val lParams = v.getLayoutParams() as RelativeLayout.LayoutParams
////                            xDelta = x - lParams.leftMargin
////                            yDelta = y - lParams.topMargin
////
////                        }
//                        MotionEvent.ACTION_MOVE -> {
//                            val layoutParams = v.getLayoutParams() as RelativeLayout.LayoutParams
//                            //Toast.makeText(this@MainActivity,xDelta.toString()+yDelta.toString(),Toast.LENGTH_SHORT).show()
//                            //layoutParams.leftMargin = x - xDelta
//                            //layoutParams.topMargin = y - yDelta
//                            //layoutParams.rightMargin = 0
//                            //layoutParams.bottomMargin = 0
//                            var min = 10000.0f
////                            var Xtemp=layoutParams.leftMargin
////                            var Ytemp=layoutParams.leftMargin
//                            var Xtemp = 0.0f
//                            var Ytemp = 0.0f
//                            for (i in 0 until sliceSize) {
//                                for (j in 0 until sliceSize) {
//                                    val temp =
//                                        sqrt((yCoordiante[i] - y+yDelta) * (yCoordiante[i] - y+yDelta) + (xCoordiante[j] - x) * (xCoordiante[j] - x))
//                                    if (min > temp) {
//                                        min = temp
//                                        Xtemp = xCoordiante[j]
//                                        Ytemp = yCoordiante[i]
//                                    }
//                                }
//                            }
//
//                            //layoutParams.leftMargin = Xtemp
//                            //layoutParams.topMargin = Ytemp
//                            imageView.x=Xtemp
//                            imageView.y=Ytemp
//
//                            Toast.makeText(this@MainActivity, yDelta.toString() + "  " + y.toString() + " " + Ytemp.toString(), Toast.LENGTH_SHORT).show()
//                            v.layoutParams = layoutParams
//                        }
//                    }
//                    relativeLayout.invalidate()
//                    true
//                })
//            }
//        }
//    }

}