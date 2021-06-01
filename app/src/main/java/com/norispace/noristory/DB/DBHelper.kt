package com.norispace.noristory.DB

import android.app.ProgressDialog
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import com.norispace.noristory.Books.BookData
import com.norispace.noristory.Model.OptionalStory_Model
import com.norispace.noristory.R
import com.norispace.service.S3Helper
import java.io.File
import java.io.FileOutputStream

class DBHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val DB_NAME = "storydb.db"
        val DB_VERSION = 1
        val TABLE_Story = "Story"
        val TABLE_OptionalStory = "OptionalStory"
    }
    private val s3Helper = S3Helper(context)


    fun firstDownload() {


        var strsql = "select thumbnail from $TABLE_Story"
        val data = ArrayList<String>()

        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val attrcount = cursor.columnCount
        Log.d("cursor", cursor.count.toString())
        cursor.moveToFirst()
        do {
            val thumbnail = cursor.getString(cursor.getColumnIndex("thumbnail"))
            data.add(thumbnail)
        } while(cursor.moveToNext())

        var strsql1 = "select image, nextImage1, nextImage2 from $TABLE_OptionalStory"
        val cursor1 = db.rawQuery(strsql1, null)
        cursor1.moveToFirst()
        do {
            val image = cursor1.getString(cursor1.getColumnIndex("image"))
            val nextImage1 = cursor1.getString(cursor1.getColumnIndex("nextImage1"))
            val nextImage2 = cursor1.getString(cursor1.getColumnIndex("nextImage2"))
            if(nextImage1 != null) data.add(nextImage1)
            if(nextImage2 != null) data.add(nextImage2)
            data.add(image)

        } while(cursor1.moveToNext())
        s3Helper.downloadImage(data)
        cursor1.close()
        cursor.close()
        db.close()

    }


    fun findStoryThumbnail(category: String?): ArrayList<BookData> {
        var strsql = "select * from $TABLE_Story"
        val data = ArrayList<BookData>()
        if(category != null)
            strsql += " where category = '$category'"

        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        cursor.moveToFirst()
        do {
            val tid = cursor.getString(0)
            val title = cursor.getString(1)
            val title_kor = cursor.getString(2)
            val thumbnail = cursor.getString(3)
            val category = cursor.getString(4)
            val imgFile = File(context.cacheDir.toString() + "/" + thumbnail)
            val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            data.add(BookData(title_kor, BitmapDrawable(bitmap),title))

        } while(cursor.moveToNext())

        //TODO db show
        cursor.close()
        db.close()
        return data

    }

    fun findOptionalStory(title:String):List<OptionalStory_Model> {
        val strsql = "select * from $TABLE_OptionalStory where title = '$title'"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val data = ArrayList<OptionalStory_Model>()
        cursor.moveToFirst()
        do {
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val image = cursor.getString(cursor.getColumnIndex("image"))
            val page = cursor.getString(cursor.getColumnIndex("page"))
            val nextPage1 = cursor.getString(cursor.getColumnIndex("nextPage1"))
            val nextPage2 = cursor.getString(cursor.getColumnIndex("nextPage2"))
            val undoPage = cursor.getString(cursor.getColumnIndex("undoPage"))
            val nextText1 = cursor.getString(cursor.getColumnIndex("nextText1"))
            val nextText2 = cursor.getString(cursor.getColumnIndex("nextText2"))
            val nextImage1 = cursor.getString(cursor.getColumnIndex("nextImage1"))
            val nextImage2 = cursor.getString(cursor.getColumnIndex("nextImage2"))
            data.add(OptionalStory_Model(title, image, page.toInt(), nextPage1.toInt(), nextPage2.toInt(), undoPage.toInt(), nextText1, nextText2, nextImage1, nextImage2))

        } while(cursor.moveToNext())
        //TODO db show
        cursor.close()
        db.close()
        return data

    }

    fun initDB() {


    }

    override fun onCreate(p0: SQLiteDatabase?) {


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table_story = "drop table if exists $TABLE_Story;"
        val drop_table_optionalstory = "drop table if exists $TABLE_OptionalStory;"
        db!!.execSQL(drop_table_story)
        db!!.execSQL(drop_table_optionalstory)
        onCreate(db)
    }

}