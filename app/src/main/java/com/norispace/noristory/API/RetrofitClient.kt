package com.norispace.noristory.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-3-37-85-50.ap-northeast-2.compute.amazonaws.com:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val API = retrofit.create(InterfaceAPI::class.java)
}