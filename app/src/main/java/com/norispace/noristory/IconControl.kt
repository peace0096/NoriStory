package com.norispace.noristory

import android.renderscript.ScriptGroup
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_subject.*
import kotlin.math.sqrt

class IconControl(var myTag: String) {

//     fun initDrag(count:Int,contentType: Int,relativeLayout:RelativeLayout,sliceSize:Int,lastTouchTag:String,xCoordinate:Array<Float>,yCoordinate:Array<Float>,contentData:ArrayList<SubjectStoryData>){
//        //val start=imageCount+textCount-1
//        var childNum=count
//        var xDelta = 0
//        var yDelta = 0
//        var locationX = 0
//        var locationY = 0
//         myTag= lastTouchTag
//         //myTag= lastTouchTag
//         Log.i("child3","asadasdasd")
//        //binding.apply {
//            //for(i in start until relativeLayout?.childCount!!){
//            relativeLayout?.getChildAt(childNum)?.setOnTouchListener(View.OnTouchListener { v, event ->
//
//                for(i in 0 until relativeLayout.childCount){
//                    if(relativeLayout.getChildAt(i)?.tag == myTag){
//                        relativeLayout.getChildAt(i).background=null
//                        break
//                    }
//                }
//                myTag = relativeLayout.getChildAt(childNum).tag.toString()
//                Log.i("child1",myTag)
//                relativeLayout.getChildAt(childNum).setBackgroundResource(R.drawable.img_border)
//                val x = event.rawX.toInt()
//                val y = event.rawY.toInt()
//                when (event.getAction() and MotionEvent.ACTION_MASK) {
//                    MotionEvent.ACTION_DOWN -> {
//                        val lParams = v.layoutParams as RelativeLayout.LayoutParams
//                        xDelta = x - lParams.leftMargin
//                        yDelta = y - lParams.topMargin
//                    }
//                    MotionEvent.ACTION_MOVE -> {
//                        val layoutParams = v.layoutParams as RelativeLayout.LayoutParams
//                        //layoutParams.leftMargin = x - xDelta
//                        //layoutParams.topMargin = y - yDelta
//                        //layoutParams.rightMargin = 0
//                        //layoutParams.bottomMargin = 0
//                        var min = 10000.0f
//                        var tempX = 0.0f
//                        var tempY = 0.0f
//                        for (i in 0 until sliceSize) {
//                            for (j in 1 until sliceSize) {
//                                val temp =
//                                    sqrt((yCoordinate[i] - y+yDelta) * (yCoordinate[i] - y+yDelta) + (xCoordinate[j] - x) * (xCoordinate[j] - x))
//                                if (min > temp) {
//                                    min = temp
//                                    tempX = xCoordinate[j-1]
//                                    tempY = yCoordinate[i]
//                                    locationX = j
//                                    locationY = i
//                                }
//                            }
//                        }
//
//                        relativeLayout.getChildAt(childNum).x=tempX
//                        relativeLayout.getChildAt(childNum).y=tempY
//                        v.layoutParams = layoutParams
//                    }
//                }
//                relativeLayout.invalidate()
//                addContentData(1,childNum,locationX,locationY,contentType,"tempname",relativeLayout,contentData)
//                true
//            })
//            //}
//
//        //}
//    }
//
//    fun getTag():String{
//        Log.i("child2",myTag)
//        return myTag
//    }


     fun addContentData( page: Int,childNum:Int,locationX: Int,locationY: Int,contentType:Int,content:String,relativeLayout:RelativeLayout,contentData:ArrayList<SubjectStoryData>){
        var sizeX=0
        var sizeY=0
        if(contentType==1){
            sizeX= relativeLayout.getChildAt(childNum).x.toInt()
            sizeY= relativeLayout.getChildAt(childNum).y.toInt()
        }
        else{
            sizeX=(relativeLayout.getChildAt(childNum) as TextView).textSize.toInt()
            sizeY=0
        }
        //binding.apply {
            contentData.add(
                SubjectStoryData(
                    page,
                    sizeX,
                    sizeY,
                    locationX,
                    locationY,
                    contentType,
                    content
                )
            )
        //}
    }
}