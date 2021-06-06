package com.norispace.noristory.MakeStory

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.norispace.noristory.DB.DBHelper
import com.norispace.noristory.ListFragment.EmoticonFragment
import com.norispace.noristory.ListFragment.MyCardListFragment
import com.norispace.noristory.MainMenu.MainActivity
import com.norispace.noristory.ManageIcon.ManageChildView
import com.norispace.noristory.MyPainterView
import com.norispace.noristory.R
import com.norispace.noristory.Repository.User_Repo
import com.norispace.noristory.ViewModel.StoryViewModel
import com.norispace.noristory.ViewModel.UserViewModel
import com.norispace.noristory.databinding.ActivityMakeCardBinding
import com.norispace.service.S3Helper
import kotlinx.android.synthetic.main.activity_make_card.*
import java.io.File
import java.io.FileOutputStream

import kotlin.math.sqrt

class MakeCardActivity : AppCompatActivity(), EmoticonFragment.OnDataPass,MyCardListFragment.OnDataPass {
    val binding by lazy {ActivityMakeCardBinding.inflate(layoutInflater)}
    lateinit var dbHelper: DBHelper
    lateinit var s3Helper: S3Helper
    lateinit var userViewModel: UserViewModel
    var mode = -1
    lateinit var ptv: MyPainterView
    val mydb = DBHelper(this)

    private val sliceSize = 5
    private var xCoordinate = Array(sliceSize, { 0.0f })
    private var yCoordinate = Array(sliceSize, { 0.0f })
    private var manageChildView=
        ManageChildView()
    private var lastTouchTag = ""
    private var emoticonNum =0 // 선택된 이모티콘 번호
    private val myCardListFragment=MyCardListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ptv = MyPainterView(this)
        binding.PainterView?.addView(ptv)
        dbHelper = DBHelper(this)
        s3Helper = S3Helper(this)
        userViewModel = UserViewModel()
        initBasiceBtn()
        initShowCards()
        initEmoticon()
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

    private fun initBasiceBtn(){
        binding.apply {
            mybookBack?.setOnClickListener {
                finish()
            }
            mybookHome?.setOnClickListener {
                val i= Intent(this@MakeCardActivity, MainActivity::class.java)
                i.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(i)
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
                lastTouchTag=manageChildView.setBorder(-1,lastTouchTag,PainterView!!)
                drawComplete()
            }
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
            var urlForS3 = User_Repo.getToken()

            var StoragePath = cacheDir.toString() + "/" + User_Repo.getToken()
            if(flag==1){
                urlForS3 += "/Image/Card/Subject"
                StoragePath += "/Image/Card/Subject"
                cardSave?.setImageResource(R.drawable.card_subject_saved)
            }else{
                urlForS3 += "/Image/Card/Character"
                StoragePath += "/Image/Card/Character"
                cardSave?.setImageResource(R.drawable.card_char_saved)
            }

            var Folder = File(StoragePath)
            if(!Folder.exists())        //폴더 없으면 생성
                Folder.mkdirs()

            var cardcount = 0
            var fileName = "card" + cardcount.toString()+".png"
            var file = File(StoragePath, fileName)

            while (file.exists())   //같은 이름이 있으면 다른 이름으로
            {
                cardcount += 1
                fileName = "card" + cardcount.toString()+".png"
                file = File(StoragePath, fileName)
            }

            PainterView?.buildDrawingCache()
            val bitmap: Bitmap? = PainterView?.getDrawingCache()
            val fos = FileOutputStream(file)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, fos) //썸네일로 사용하므로 퀄리티를 낮게설정
            fos.close();
            setImage?.setImageBitmap(bitmap)
            PainterView?.removeAllViews()

            mydb.insertCard(StoragePath+"/"+fileName)
            val data = ArrayList<String>()
            data.add(urlForS3 + "/" + fileName)
            s3Helper.uploadImage(data)
            userViewModel.insertCard(urlForS3 + "/" + fileName)
            screenBlur?.setOnClickListener {
                screenBlur?.visibility=View.GONE
                saveComplete?.visibility= View.GONE
            }

        }
    }

    private fun initShowCards(){
        binding.apply {
            val fragment=supportFragmentManager.beginTransaction()
            fragment.replace(R.id.myCardFragment,myCardListFragment)
            fragment.commit()
            cardShowBtn?.setOnClickListener {
                chooseCardKind?.visibility=View.GONE
                screenBlur?.visibility=View.VISIBLE
                myCardFragment?.visibility=View.VISIBLE
//                cancleBtnBig?.setOnClickListener {
//                    screenBlur?.visibility=View.GONE
//                    showCrads?.visibility=View.GONE
//                }
            }

        }
    }
    override fun onEmoticonPass(data: Int) { //프래그먼트에서 이모티콘 정보 받아와 이모티콘 출력
        emoticonNum=data
        binding.apply {
            val layout=LinearLayout(this@MakeCardActivity)
            layout.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.orientation=LinearLayout.HORIZONTAL
            layout.tag="layout" + (manageChildView.imageCount+manageChildView.textCount).toString()
            val imageView = ImageView(this@MakeCardActivity)
            imageView.layoutParams = LinearLayout.LayoutParams(200, 200)
            imageView.tag = "image" + manageChildView.imageCount.toString()
            imageView.setImageResource(emoticonNum)
            val cancleView = ImageView(this@MakeCardActivity)
            cancleView.layoutParams = LinearLayout.LayoutParams(120, 120)
            cancleView.setImageResource(R.drawable.cancle_btn_small)
            cancleView.setOnClickListener {
                deleteEmoticon()
            }
            layout.addView(imageView)
            layout.addView(cancleView)
            manageChildView.imageCount++
            PainterView?.addView(layout)
            initDrag(manageChildView.imageCount+manageChildView.textCount,1)
        }
    }

    private fun deleteEmoticon(){
        binding.apply {
            val num=manageChildView.deleteChild(lastTouchTag,PainterView!!)
            for(i in num until PainterView?.childCount!!){
                val latestChild = PainterView.getChildAt(i) as LinearLayout
                if(latestChild.getChildAt(0) is ImageView) { // 1 번이 삭제버튼 0번이 이미지 or 텍스트 source
                    initDrag(i,1)
                }else{
                    initDrag(i,2)
                }
            }
        }

    }

    private fun initEmoticon(){
        binding.apply {
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



    private fun initDrag(count:Int,contentType: Int){
        var childNum=count
        var xDelta = 0
        var yDelta = 0
        var locationX = 0
        var locationY = 0
        binding.apply {

            PainterView?.getChildAt(childNum)?.setOnTouchListener(View.OnTouchListener { v, event ->
                lastTouchTag=manageChildView.setBorder(childNum,lastTouchTag,PainterView)
                val x = event.rawX.toInt()
                val y = event.rawY.toInt()
                when (event.getAction() and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_DOWN -> {
                        val lParams = v.layoutParams as FrameLayout.LayoutParams
                        xDelta = x - lParams.leftMargin
                        yDelta = y - lParams.topMargin
                    }
                    MotionEvent.ACTION_MOVE -> {
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

    override fun onSelectedCardPass(data: ArrayList<Int>) {
        binding.apply{
            if(data[0]==-1){
                screenBlur?.visibility=View.GONE
                myCardFragment?.visibility=View.GONE
            }
        }
    }
}