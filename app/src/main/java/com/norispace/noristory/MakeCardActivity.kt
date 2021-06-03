package com.norispace.noristory

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.norispace.noristory.databinding.ActivityMakeCardBinding
import kotlinx.android.synthetic.main.activity_make_card.*

import java.io.File
import java.io.FileOutputStream

class MakeCardActivity : AppCompatActivity() {
    val binding by lazy {ActivityMakeCardBinding.inflate(layoutInflater)}
    var mode = -1
    lateinit var ptv:MyPainterView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ptv = MyPainterView(this)
        binding.PainterView?.addView(ptv)
        initbtn()
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

                var StoragePath = "/data/data/com.norispace.noristory/cache/Image/Card"
                var Folder = File(StoragePath)
                if(!Folder.exists())        //폴더 없으면 생성
                    Folder.mkdirs()

                val fileName = "card" + ".jpg";

                ptv.buildDrawingCache()
                val bitmap: Bitmap = ptv.getDrawingCache()
                val file = File(StoragePath, fileName)
                val fos = FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos); //썸네일로 사용하므로 퀄리티를 낮게설정
                fos.close();

            }
            imoticonBtn?.setOnClickListener {

            }
        }
    }
}