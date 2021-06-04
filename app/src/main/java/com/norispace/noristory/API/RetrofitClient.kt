package com.norispace.noristory.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {

    interface callback {
        fun callbackMethod(isSuccessful: Boolean, result: String?)
    }

    private lateinit var interceptor:HttpLoggingInterceptor


    fun getBaseClient() : InterfaceAPI{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        val url = "http://3.36.145.248:3000"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(InterfaceAPI::class.java)

    }


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.36.145.248:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val API = retrofit.create(InterfaceAPI::class.java)
}