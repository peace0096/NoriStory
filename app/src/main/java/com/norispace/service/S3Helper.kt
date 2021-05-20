package com.norispace.service

import android.content.Context
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import java.net.URL
import java.util.*

class S3Helper(val context:Context?) {
    companion object {
        val POOLID = "ap-northeast-2:167efb36-dea5-4724-935d-0c419fc48f12"
        val REGION = Regions.AP_NORTHEAST_2
        val BUCKET_NAME = "noristory"
    }

    fun getImage(fileName:String): URL? {
        val credentialsProvider = CognitoCachingCredentialsProvider(
            context,
            "ap-northeast-2:945ced75-76a6-4c46-9c7b-2f778daef3bc", // 자격 증명 풀 ID
            Regions.AP_NORTHEAST_2 // 리전
        );

        TransferNetworkLossHandler.getInstance(context)


        val expires = Date (Date().time + 1000 * 120);
        val generatePresignedUrlRequest = GeneratePresignedUrlRequest(BUCKET_NAME, fileName)
        generatePresignedUrlRequest.expiration = expires
        val s3client = AmazonS3Client(credentialsProvider, Region.getRegion(REGION))
        val url = s3client.generatePresignedUrl(generatePresignedUrlRequest)



        return url
    }


}