package com.norispace.noristory.ManageIcon

import android.view.View
import android.widget.*
import com.norispace.noristory.Model.SubjectStoryData
import com.norispace.noristory.R

class ManageChildView {
    var contentData= ArrayList<SubjectStoryData>()
    var imageCount=0
    var textCount=0

    fun deleteChild(lastTouchTag:String,myLayout:FrameLayout):Int{
        for (i in 0 until myLayout?.childCount!!) {
            if (myLayout.getChildAt(i)?.tag == lastTouchTag) {
                val latestChild = myLayout?.getChildAt(i) as LinearLayout
                if(latestChild.getChildAt(0) is ImageView){
                    imageCount-=1
                }else{
                    textCount-=1
                }
                myLayout.removeViewAt(i)
                val index=i-1  // 1번째 child가 0번째 index에서부터 저장되므로!
                contentData.removeAt(index)
                return i
            }
        }
        return -1
    }

    fun setBorder(childNum:Int,lastTouchTag:String,myLayout:FrameLayout):String{
        for(i in 0 until myLayout.childCount){
            if(myLayout.getChildAt(i)?.tag == lastTouchTag){
                val latestChild = myLayout?.getChildAt(i) as LinearLayout
                latestChild.getChildAt(1).visibility= View.GONE
                latestChild.getChildAt(0).background=null // 1 번이 삭제버튼 0번이 이미지 or 텍스트 source
                break
            }
        }
        if(childNum<0) {
            return "finish"
        }
        else {
            val layout =
                myLayout.getChildAt(childNum) as LinearLayout  // PainterView 안에 LinearLayout 위치함

            layout.getChildAt(1).visibility = View.VISIBLE
            layout.getChildAt(0).setBackgroundResource(R.drawable.img_border)
            return myLayout.getChildAt(childNum).tag.toString()
        }
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

    fun updateContentData( page: Int,childNum:Int,locationX: Int,locationY: Int,contentType:Int,content:String,myLayout:FrameLayout){
        var sizeX=0
        var sizeY=0
        if(contentType==1){
            sizeX= myLayout.getChildAt(childNum).x.toInt()
            sizeY= myLayout.getChildAt(childNum).y.toInt()
        }
        else{
            sizeX=(myLayout.getChildAt(childNum) as TextView).textSize.toInt()
            sizeY=0
        }
        if(contentData.size<childNum){ //이모티콘이 추가된 경우
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
            val index=childNum-1 // childNum은 1부터 시작임
            contentData[index].page=page
            contentData[index].sizeX=sizeX
            contentData[index].sizeY=sizeY
            contentData[index].locationX=locationX
            contentData[index].locationY=locationY
            contentData[index].contentType=contentType
            contentData[index].content=content
        }

    }
}