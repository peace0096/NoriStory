package com.norispace.noristory


import android.content.Context
import android.graphics.*
import android.os.Environment
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream


class MyPainterView(context: Context):View(context) {

    val paint = Paint()
    lateinit var path:Path
    var line = mutableListOf<Point>()
    var lines = mutableListOf<Path>()
    var linesState = mutableListOf<List<Any>>()
    var tempSave = mutableListOf<Path>()
    var tempState = mutableListOf<List<Any>>()
    var oldX: Float = 0.0f
    var oldY: Float = 0.0f
    var color = Color.BLACK
    var size = 10.0f
    var mode = -1


    data class Point(
        val x: Float?,
        val y: Float?,
        val state: Int,
        val color: Int?,
        val size: Float?
    )

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)
        paint.style = Paint.Style.STROKE; // 선이 그려지도록
        if(lines.isNotEmpty())
            for(i in lines.indices) {
                paint.color= linesState[i][0] as Int
                paint.strokeWidth = linesState[i][1] as Float
                canvas?.drawPath(lines[i], paint)
            }


        for (j in line) {
            var newX: Float = j.x!!.toFloat()
            var newY: Float = j.y!!.toFloat()
            paint.color = j.color!!
            paint.strokeWidth = j.size!!

            if (j.state == 0) {
                path = Path()
                path.moveTo(newX, newY)
                oldX = newX
                oldY = newY
                canvas?.drawPath(path, paint)
            } else if (j.state == 1) {
                path.quadTo(oldX, oldY, (oldX + newX) / 2, (oldY + newY) / 2)
                oldX = newX
                oldY = newY
                canvas?.drawPath(path, paint)
            } else {
                path.lineTo(newX, newY)
                canvas?.drawPath(path, paint)
                lines.add(path)
                var state = listOf<Any>(j.color, j.size)
                linesState.add(state)
                if(tempSave.isNotEmpty())   //임시 저장소가 차있는데 새로 그린 경우 임시 저장소를 비워라
                {
                    tempSave.clear()
                    tempState.clear()
                }
                line.clear()
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        var x = event?.x
        var y = event?.y

        when (mode) {
            1 -> {
                color = Color.BLACK
                size = 10.0f
            }
            2 -> {
                color = Color.BLACK
                size = 20.0f
            }
            3 -> {
                color = Color.RED
                size = 10.0f
            }
            4 -> {
                color = Color.rgb(255,127,39)
                size = 10.0f
            }
            5 -> {
                color = Color.rgb(255,201,14)
                size = 10.0f
            }
            6 -> {
                color = Color.GREEN
                size = 10.0f
            }
            7 -> {
                color = Color.rgb(0,162,232)
                size = 10.0f
            }
            8-> {
                color = Color.BLUE
                size = 10.0f
            }
            9 -> {
                color = Color.rgb(163,73,164)
                size = 10.0f
            }
            10 -> {
                color = Color.MAGENTA
                size = 10.0f
            }
            11 -> {
                color = Color.WHITE
                size = 10.0f
            }
        }
        if (mode != 0) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    line.add(Point(x, y, 0, color, size))
                    invalidate()
                    return true
                }

                MotionEvent.ACTION_MOVE -> {
                    line.add(Point(x, y, 1, color, size))
                    invalidate()
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    line.add(Point(x, y, 2, color, size))
                    invalidate()
                    return true
                }
            }
            return false
        }
        else
            return true
    }
}