package com.example.imsihyun.android_server_hapdong

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController : Application(){

    lateinit var networkService: NetworkService
    private val baseUrl = "http://13.124.137.139:3000/"

    companion object {
        lateinit var instance : ApplicationController
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetwork()
    }


    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        networkService = retrofit.create(NetworkService::class.java)
    }
}