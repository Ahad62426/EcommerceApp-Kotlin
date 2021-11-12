package com.example.ecommerceapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.MyApplication
import com.example.ecommerceapp.di.RetrofitService
import com.example.ecommerceapp.dataModel.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var mService: RetrofitService

    private var products: MutableLiveData<List<Product>>
    private var showLoadingMessage = MutableLiveData<Boolean>(false)

    init {
        (application as MyApplication).getRetrofitComponet()
            .inject(this)
        products = MutableLiveData()
    }

    fun getProductsObserver(): MutableLiveData<List<Product>> {
        return products
    }

    fun getLoadingState(): MutableLiveData<Boolean> {
        return showLoadingMessage
    }

    fun fetchProductsFromAPI() {
        showLoadingMessage.postValue(true)
        val call: Call<List<Product>>? = mService.getProductsList()
        call?.enqueue(object: Callback<List<Product>> {

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                showLoadingMessage.postValue(false)
                if (response.isSuccessful) {
                    println(products)
                    products.postValue(response.body())
                } else products.postValue(null)
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                showLoadingMessage.postValue(false)
                products.postValue(null)
            }

        })
    }
}
