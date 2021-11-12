package com.example.ecommerceapp

import android.app.Application
import com.example.ecommerceapp.di.DaggerRetrofitComponet
import com.example.ecommerceapp.di.RetrofitComponet
import com.example.ecommerceapp.di.RetrofitModule

class MyApplication: Application() {

    private lateinit var retrofitComponet: RetrofitComponet

    override fun onCreate() {
        super.onCreate()
        retrofitComponet = DaggerRetrofitComponet.builder()
            .retrofitModule(RetrofitModule())
            .build()
    }

    fun getRetrofitComponet(): RetrofitComponet {
        return retrofitComponet
    }
}