package com.example.ecommerceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerceapp.adapters.ProductsAdapter
import com.example.ecommerceapp.dataModel.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_products_details.*

class ProductDetails : AppCompatActivity() {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_details)

        product = intent.getParcelableExtra<Product>("product")!!
        initView(product)
    }

    private fun initView(product: Product) {
        Glide.with(imageView)
            .load(product.image)
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)
        tvTitle.setText(product.title)
        tvCategory.setText("Category: " + product.category)
        tvDescription.setText(product.description)
        tvPrice.setText("Price: $" + product.price)
    }
}