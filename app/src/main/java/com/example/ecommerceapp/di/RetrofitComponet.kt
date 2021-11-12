package com.example.ecommerceapp.di

import com.example.ecommerceapp.viewModel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponet {

    fun inject(mainActivityViewModel: MainActivityViewModel)
}