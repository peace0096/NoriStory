package com.norispace.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-3-37-85-50.ap-northeast-2.compute.amazonaws.com:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val service = retrofit.create(
        ServiceApp::class.java)

}