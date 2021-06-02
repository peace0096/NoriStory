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
                    pencilBtn?.setImageResource(R.drawable.pencil_small)
                    penBtn?.setImageResource(R.drawable.pen_small)
                    crayongBtn?.setImageResource(R.drawable.crayong_small)
                    eraserBtn?.setImageResource(R.drawable.eraser_small)
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
                    pencilBtn?.setImageResource(R.drawable.pencil_small)
                    penBtn?.setImageResource(R.drawable.pen_small)
                    crayongBtn?.setImageResource(R.drawable.crayong_small)
                    eraserBtn?.setImageResource(R.drawable.eraser_small)
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
                if (mode >= 3 && mode <= 13 )       //이미 크레용 모드였던 경우 비활성화
                {
                    Buttonlayout?.visibility = View.INVISIBLE
                    Colorlayout?.visibility = View.VISIBLE
                }
                else
                {
                    pencilBtn?.setImageResource(R.drawable.pencil2)
                    penBtn?.setImageResource(R.drawable.pen2)
                    Buttonlayout?.visibility = View.INVISIBLE
                    Colorlayout?.visibility = View.VISIBLE
                }
            }
            Red?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 3
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong1)
            }
            Orange?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 4
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong2)
            }
            LightOrange?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 5
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong3)
            }
//            Yellow?.setOnClickListener{
//                Buttonlayout?.visibility = View.VISIBLE
//                Colorlayout?.visibility = View.INVISIBLE
//                mode = 6
//                ptv.mode = mode
//                crayongBtn?.setImageResource(R.drawable.crayong4)
//            }
//            LightGreen?.setOnClickListener{
//                Buttonlayout?.visibility = View.VISIBLE
//                Colorlayout?.visibility = View.INVISIBLE
//                mode = 7
//                ptv.mode = mode
//                crayongBtn?.setImageResource(R.drawable.crayong5)
//            }
            Green?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 8
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong6)
            }
            SkyBlue?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 9
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong7)
            }
            Blue?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 10
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong8)
            }
            Purple?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 11
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong9)
            }
            Magenta?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 12
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong10)
            }
            White?.setOnClickListener{
                Buttonlayout?.visibility = View.VISIBLE
                Colorlayout?.visibility = View.INVISIBLE
                mode = 13
                ptv.mode = mode
                crayongBtn?.setImageResource(R.drawable.crayong11)
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

        }
    }
}