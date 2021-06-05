package com.norispace.noristory

import android.widget.*

class ManageChildView {
    var contentData= ArrayList<SubjectStoryData>()
    var imageCount=0
    var textCount=0
    //lateinit var myPainterView:MyPainterView
    fun deleteChild(lastTouchTag:String,relativeLayout:RelativeLayout):Int{
        //var num=0
        for (i in 0 until relativeLayout?.childCount!!) {
            if (relativeLayout.getChildAt(i)?.tag == lastTouchTag) {
                if(relativeLayout.getChildAt(i) is ImageView){
                    imageCount-=1
                }else{
                    textCount-=1
                }
                relativeLayout.removeViewAt(i)
                contentData.removeAt(i)
                //num=i
                return i
            }
        }

//        for(i in num until relativeLayout?.childCount!!){
//            if(relativeLayout.getChildAt(i) is ImageView) {
//                initDrag(i,1)
//            }else{
//                initDrag(i,2)
//            }
//        }
        return 100
    }

    fun biggerChild(lastTouchTag:String,relativeLayout:RelativeLayout){
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
//                    Toast.makeText(this, (relativeLayout.getChildAt(i) as TextView).text.toString(),
//                        Toast.LENGTH_SHORT).show()
                    (relativeLayout.getChildAt(i) as TextView).textSize*=1.5f
                    contentData[i].sizeX= (relativeLayout.getChildAt(i) as TextView).textSize.toInt()
                }
                relativeLayout.getChildAt(i).requestLayout()
                break
            }

        }
    }

    fun smallerChild(lastTouchTag:String,relativeLayout:RelativeLayout){
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
//                    Toast.makeText(this, (relativeLayout.getChildAt(i) as TextView).text.toString(),Toast.LENGTH_SHORT).show()
                    (relativeLayout.getChildAt(i) as TextView).textSize=(relativeLayout.getChildAt(i) as TextView).textSize/2.0f
                    contentData[i].sizeX= (relativeLayout.getChildAt(i) as TextView).textSize.toInt()
                }
                relativeLayout.getChildAt(i).requestLayout()
                break
            }
        }
    }

    fun updateContentData( page: Int,childNum:Int,locationX: Int,locationY: Int,contentType:Int,content:String,relativeLayout:FrameLayout){
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
        if(contentData[childNum]==null){
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
        }else{
            contentData[childNum].page=page
            contentData[childNum].sizeX=sizeX
            contentData[childNum].sizeY=sizeY
            contentData[childNum].locationX=locationX
            contentData[childNum].locationY=locationY
            contentData[childNum].contentType=contentType
            contentData[childNum].content=content
        }

    }
}