package com.norispace.noristory.MakeStory

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.norispace.noristory.ManageIcon.ManageChildView
import com.norispace.noristory.MyPainterView
import com.norispace.noristory.R
import com.norispace.noristory.databinding.ActivityMakeCardBinding
import kotlinx.android.synthetic.main.activity_make_card.*
import java.io.File

import kotlin.math.sqrt

class MakeCardActivity : AppCompatActivity() {
    val binding by lazy {ActivityMakeCardBinding.inflate(layoutInflater)}
    var mode = -1
    lateinit var ptv: MyPainterView

    private val sliceSize = 5
    private var xCoordinate = Array(sliceSize, { 0.0f })
    private var yCoordinate = Array(sliceSize, { 0.0f })
    private var manageChildView=
        ManageChildView()
    private var lastTouchTag = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ptv = MyPainterView(this)
        binding.PainterView?.addView(ptv)
        initbtn()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        binding.apply {
            for (i in 1 until sliceSize) {
                for (j in 1..i) {
                    yCoordinate[i] = yCoordinate[i] + (PainterView?.height?.div(sliceSize) ?: 0)
                    xCoordinate[i] = xCoordinate[i] + (PainterView?.width?.div(sliceSize) ?: 0)
                }
            }
        }
    }

    fun initbtn() {
        binding.apply {
            eraserBtn?.setOnClickListener {
                eraserBtn?.setImageResource(R.drawable.eraser_small)
                pencilBtn?.setImageResource(R.drawable.pencil_blur)
                penBtn?.setImageResource(R.drawable.pen_blur)
                crayongBtn?.setImageResource(R.drawable.crayong_blur)
                ptv.lines.clear()
                ptv.linesState.clear()
                ptv.tempSave.clear()
                ptv.tempState.clear()
                ptv.invalidate()
            }
            pencilBtn?.setOnClickListener {
                if (mode == 1)       //이미 연필 모드였던 경우 비활성화
                {
                    mode = 0
                    pencilBtn?.setImageResource(R.drawable.pencil_blur)
                }
                else {
                    mode = 1
                    pencilBtn?.setImageResource(R.drawable.pencil_big)
                    penBtn?.setImageResource(R.drawable.pen_blur)
                    crayongBtn?.setImageResource(R.drawable.crayong_blur)
                    eraserBtn?.setImageResource(R.drawable.eraser_blur)
                }
                ptv.mode = mode
            }
            penBtn?.setOnClickListener {
                if (mode == 2)       //이미 펜 모드였던 경우 비활성화
                {
                    mode = 0
                    penBtn?.setImageResource(R.drawable.pen_blur)
                }
                else {
                    mode = 2
                    pencilBtn?.setImageResource(R.drawable.pencil_blur)
                    penBtn?.setImageResource(R.drawable.pen_big)
                    crayongBtn?.setImageResource(R.drawable.crayong_blur)
                    eraserBtn?.setImageResource(R.drawable.eraser_blur)
                }
                ptv.mode = mode
            }
            crayongBtn?.setOnClickListener {
                if (mode >= 3 && mode <= 11 )       //이미 크레용 모드였던 경우 비활성화
                {
                    Buttonlayout?.visibility = View.INVISIBLE
                    Colorlayout?.visibility = View.VISIBLE
                }
                else
                {
                    pencilBtn?.setImageResource(R.drawable.pencil_blur)
                    penBtn?.setImageResource(R.drawable.pen_blur)
                    crayongBtn?.setImageResource(R.drawable.crayong_blur)
                    Buttonlayout?.visibility = View.INVISIBLE
                    Colorlayout?.visibility = View.VISIBLE
                }
            }
            crayon_cancle_btn.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                if(mode == 1)
                    pencilBtn?.setImageResource(R.drawable.pencil_big)
                else if(mode == 2)
                    penBtn?.setImageResource(R.drawable.pen_big)
                else if(mode == 3)
                    crayongBtn?.setImageResource(R.drawable.crayong_red_big)
                else if(mode == 4)
                    crayongBtn?.setImageResource(R.drawable.crayong_orange_big)
                else if(mode == 5)
                    crayongBtn?.setImageResource(R.drawable.crayong_lightorange_big)
                else if(mode == 6)
                    crayongBtn?.setImageResource(R.drawable.crayong_green_big)
                else if(mode == 7)
                    crayongBtn?.setImageResource(R.drawable.crayong_skyblue_big)
                else if(mode == 8)
                    crayongBtn?.setImageResource(R.drawable.crayong_blue_big)
                else if(mode == 9)
                    crayongBtn?.setImageResource(R.drawable.crayong_purple_big)
                else if(mode == 10)
                    crayongBtn?.setImageResource(R.drawable.crayong_magenta_big)
                else if(mode == 11)
                    crayongBtn?.setImageResource(R.drawable.crayong_white_big)


                Red?.setImageResource(R.drawable.crayong_red_big)
                Orange?.setImageResource(R.drawable.crayong_orange_big)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_big)
                Green?.setImageResource(R.drawable.crayong_green_big)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_big)
                Blue?.setImageResource(R.drawable.crayong_blue_big)
                Purple?.setImageResource(R.drawable.crayong_purple_big)
                Magenta?.setImageResource(R.drawable.crayong_magenta_big)
                White?.setImageResource(R.drawable.crayong_white_big)
            }
            Red?.setOnClickListener{
                mode = 3
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_big)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            Orange?.setOnClickListener{
                mode = 4
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_big)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            LightOrange?.setOnClickListener{
                mode = 5
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_big)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            Green?.setOnClickListener{
                mode = 6
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_big)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            SkyBlue?.setOnClickListener{
                mode = 7
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_big)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            Blue?.setOnClickListener{
                mode = 8
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_big)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            Purple?.setOnClickListener{
                mode = 9
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_big)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            Magenta?.setOnClickListener{
                mode = 10
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_big)
                White?.setImageResource(R.drawable.crayong_white_blur)
            }
            White?.setOnClickListener{
                mode = 11
                ptv.mode = mode
                Red?.setImageResource(R.drawable.crayong_red_blur)
                Orange?.setImageResource(R.drawable.crayong_orange_blur)
                LightOrange?.setImageResource(R.drawable.crayong_lightorange_blur)
                Green?.setImageResource(R.drawable.crayong_green_blur)
                SkyBlue?.setImageResource(R.drawable.crayong_skyblue_blur)
                Blue?.setImageResource(R.drawable.crayong_blue_blur)
                Purple?.setImageResource(R.drawable.crayong_purple_blur)
                Magenta?.setImageResource(R.drawable.crayong_magenta_blur)
                White?.setImageResource(R.drawable.crayong_white_big)
            }
            BackBtn?.setOnClickListener{
                if(ptv.lines.isNotEmpty())
                {
                    ptv.tempSave.add(ptv.lines.last())
                    ptv.tempState.add(ptv.linesState.last())
                    ptv.lines.removeAt(ptv.lines.size - 1)
                    ptv.linesState.removeAt(ptv.linesState.size - 1)
                }
                ptv.invalidate()
            }
            RePlayBtn?.setOnClickListener{
                if(ptv.tempSave.isNotEmpty()) {
                    ptv.lines.add(ptv.tempSave.last())
                    ptv.linesState.add(ptv.tempState.last())
                    ptv.tempSave.removeAt(ptv.tempSave.size - 1)
                    ptv.tempState.removeAt(ptv.tempState.size - 1)
                }

                ptv.invalidate()
            }
            card_saveBtn?.setOnClickListener {
                drawComplete()
            }

//            imoticonBtn?.setOnClickListener {
//                val layout=LinearLayout(this@MakeCardActivity)
//                layout.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
//                layout.orientation=LinearLayout.HORIZONTAL
//                layout.tag="layout" + manageChildView.imageCount.toString()
//                val imageView = ImageView(this@MakeCardActivity)
//                imageView.layoutParams = LinearLayout.LayoutParams(200, 200)
//                imageView.tag = "image" + manageChildView.imageCount.toString()
//                imageView.setImageResource(R.drawable.ic_android_black_24dp)
//                val cancleView = ImageView(this@MakeCardActivity)
//                cancleView.layoutParams = LinearLayout.LayoutParams(120, 120)
//                cancleView.setImageResource(R.drawable.cancle_btn_small)
//                layout.addView(imageView)
//                layout.addView(cancleView)
//                manageChildView.imageCount++
//                PainterView?.addView(layout)
//                initDrag(manageChildView.imageCount+manageChildView.textCount,1)
//            }
        }
    }

    private fun drawComplete(){
        binding.apply {
            var flag=1
            screenBlur?.visibility=View.VISIBLE
            chooseCardKind?.visibility=View.VISIBLE
            subjectCard?.setOnClickListener {
                flag=1
                saveCard(flag)
            }
            characterCard?.setOnClickListener {
                flag=2
                saveCard(flag)
            }

        }
    }

    private fun saveCard(flag:Int){
        binding.apply {
            chooseCardKind?.visibility=View.GONE
            saveComplete?.visibility=View.VISIBLE
            cardSave?.visibility= View.VISIBLE
            if(flag==1){
                ///////////////////////////////////////주제 사진 저장하기
//                var StoragePath = "/data/data/com.norispace.noristory/cache/Image/Card"
//                var Folder = File(StoragePath)
//                if(!Folder.exists())        //폴더 없으면 생성
//                    Folder.mkdirs()
//
//                val fileName = "card" + ".jpg";
//
//                PainterView?.buildDrawingCache()
//                val bitmap: Bitmap? = PainterView?.getDrawingCache()
//                val file = File(StoragePath, fileName)
//                val fos = FileOutputStream(file);
//                bitmap?.compress(Bitmap.CompressFormat.PNG, 100, fos); //썸네일로 사용하므로 퀄리티를 낮게설정
//                fos.close();

                cardSave?.setImageResource(R.drawable.card_subject_saved)
            }else{
                ///////////////////////////////////////캐릭터 사진 저장하기
                cardSave?.setImageResource(R.drawable.card_char_saved)
            }
            //setImage?.setImageResource()        저장된 사진 보여주기
            screenBlur?.setOnClickListener {
                screenBlur?.visibility=View.GONE
                saveComplete?.visibility= View.GONE
            }
        }
    }

    private fun initDrag(count:Int,contentType: Int){
        var childNum=count
        var xDelta = 0
        var yDelta = 0
        var locationX = 0
        var locationY = 0
        binding.apply {
            val layout=PainterView?.getChildAt(childNum) as LinearLayout  // PainterView 안에 LinearLayout 위치함
            PainterView.getChildAt(childNum)?.setOnTouchListener(View.OnTouchListener { v, event ->
                for(i in 0 until PainterView.childCount){
                    if(PainterView.getChildAt(i)?.tag == lastTouchTag){
                        //PainterView.getChildAt(i).background=null
                        val latestChild = PainterView?.getChildAt(i) as LinearLayout
                        latestChild.getChildAt(1).visibility=View.GONE
                        latestChild.getChildAt(0).background=null // 1 번이 삭제버튼 0번이 이미지 or 텍스트 source
                        break
                    }
                }
                lastTouchTag = PainterView.getChildAt(childNum).tag.toString()
                layout.getChildAt(1).visibility=View.VISIBLE
                layout.getChildAt(0).setBackgroundResource(R.drawable.img_border)
                //PainterView.getChildAt(childNum).setBackgroundResource(R.drawable.img_border)
                val x = event.rawX.toInt()
                val y = event.rawY.toInt()
                when (event.getAction() and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_DOWN -> {
                        //val lParams = v.layoutParams as RelativeLayout.LayoutParams
                        val lParams = v.layoutParams as FrameLayout.LayoutParams
                        xDelta = x - lParams.leftMargin
                        yDelta = y - lParams.topMargin
                    }
                    MotionEvent.ACTION_MOVE -> {
                        //val layoutParams = v.layoutParams as RelativeLayout.LayoutParams
                        val layoutParams = v.layoutParams as FrameLayout.LayoutParams
                        var min = 1000.0f
//                        var tempX = 0.0f
//                        var tempY = 0.0f
                        for (i in 0 until sliceSize) {
                            for (j in 0 until sliceSize) {
                                val temp =
                                    sqrt((yCoordinate[i] - y+yDelta) * (yCoordinate[i] - y+yDelta) + (xCoordinate[j] - x+xDelta) * (xCoordinate[j] - x+xDelta))
                                if (min > temp) {
                                    min = temp
//                                    tempX = xCoordinate[j]
//                                    tempY = yCoordinate[i]
                                    PainterView.getChildAt(childNum).x=xCoordinate[j]
                                    PainterView.getChildAt(childNum).y=yCoordinate[i]
                                    locationX = j
                                    locationY = i
                                }
                            }
                        }

//                        PainterView.getChildAt(childNum).x=tempX
//                        PainterView.getChildAt(childNum).y=tempY
                        v.layoutParams = layoutParams

                    }
                }
                PainterView.invalidate()
                manageChildView.updateContentData(1,childNum,locationX,locationY,contentType,"tempname",PainterView)
                true
            })
        }
    }
}