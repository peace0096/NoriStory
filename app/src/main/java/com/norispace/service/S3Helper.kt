package com.norispace.service

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.norispace.noristory.Repository.User_Repo
import java.io.File
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class S3Helper(val context:Context) {
    companion object {
        val POOLID = "ap-northeast-2:ce47afd6-11c5-41b1-b7ab-ee0505bbe81b"
        val REGION = Regions.AP_NORTHEAST_2
        val BUCKET_NAME = "norisbucket"
    }

    init {

    }

    fun uploadImage(data: ArrayList<String>) {
        val credentialsProvider = CognitoCachingCredentialsProvider(
            context,
            POOLID, // 자격 증명 풀 ID
            REGION // 리전
        );
        TransferNetworkLossHandler.getInstance(context)

        // TransferUtility 객체 생성
        val transferUtility = TransferUtility.builder()
            .context(context)
            .defaultBucket(BUCKET_NAME) // 디폴트 버킷 이름.
            .s3Client(AmazonS3Client(credentialsProvider, Region.getRegion(REGION)))
            .build()

        for(i in 0 until data.size) {
            val uploadObserver = transferUtility.upload(data[i], File(context.cacheDir.toString() + "/" + User_Repo.getToken() +" /" + data[i]))
            Log.i("upload data", data[i].toString())
            val progressDialog = ProgressDialog(context)
            progressDialog.setMessage("다운로드 중...")
            progressDialog.setCancelable(true)
            progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal)
            progressDialog.show()

            uploadObserver.setTransferListener(object : TransferListener {
                override fun onProgressChanged(id: Int, current: Long, total: Long) {
                    try {
                        val done = (((current.toDouble() / total) * 100.0).toInt()) //as Int
                        Log.d("AWS", "UPLOAD - - ID: $id, percent done = $done")
                    }
                    catch (e: Exception) {
                        Log.d("AWS", "Trouble calculating progress percent", e)
                    }
                }

                override fun onStateChanged(id: Int, state: TransferState?) {
                    if (state == TransferState.COMPLETED) {
                        progressDialog.dismiss()
                        Log.d("AWS", "UPLOAD Completed!")
                    }
                }
                override fun onError(id: Int, ex: java.lang.Exception) {
                    Log.d("AWS", "UPLOAD ERROR - - ID: $id - - EX: ${ex.message.toString()}")
                }


            })

        }
    }



    fun downloadImage(data: ArrayList<String>) {

        val credentialsProvider = CognitoCachingCredentialsProvider(
            context,
            POOLID, // 자격 증명 풀 ID
            REGION // 리전
        );
        TransferNetworkLossHandler.getInstance(context)

        // TransferUtility 객체 생성
        val transferUtility = TransferUtility.builder()
            .context(context)
            .defaultBucket(BUCKET_NAME) // 디폴트 버킷 이름.
            .s3Client(AmazonS3Client(credentialsProvider, Region.getRegion(REGION)))
            .build()

        // 다운로드 실행. object: "SomeFile.mp4". 두 번째 파라메터는 Local경로 File 객체.
        for(i in 0 until data.size) {
            Log.i("download data", data[i].toString())
            val progressDialog = ProgressDialog(context)
            progressDialog.setMessage("다운로드 중...")
            progressDialog.setCancelable(true)
            progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal)
            progressDialog.show()

            val downloadObserver = transferUtility.download(data[i], File(context.cacheDir.toString() + "/" + data[i]))

            //TODO 다운받는 과정을 다이얼로그 + 프로그래스바로 표현

            // 다운로드 과정을 알 수 있도록 Listener를 추가할 수 있다.
            downloadObserver.setTransferListener(object : TransferListener {
                override fun onStateChanged(id: Int, state: TransferState) {
                    if (state == TransferState.COMPLETED) {
                        progressDialog.dismiss()
                        Log.d("AWS", "DOWNLOAD Completed!")
                    }
                }

                override fun onProgressChanged(id: Int, current: Long, total: Long) {
                    try {
                        val done = (((current.toDouble() / total) * 100.0).toInt()) //as Int
                        Log.d("AWS", "DOWNLOAD - - ID: $id, percent done = $done")
                    }
                    catch (e: Exception) {
                        Log.d("AWS", "Trouble calculating progress percent", e)
                    }
                }

                override fun onError(id: Int, ex: Exception) {
                    Log.d("AWS", "DOWNLOAD ERROR - - ID: $id - - EX: ${ex.message.toString()}")
                }
            })
        }



    }


}