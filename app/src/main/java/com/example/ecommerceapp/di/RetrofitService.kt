package com.example.ecommerceapp.di

import com.example.ecommerceapp.dataModel.Product
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("products")
    fun getProductsList(): Call<List<Product>>?
}