package com.example.ecommerceapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.adapters.ProductsAdapter
import com.example.ecommerceapp.dataModel.Product
import com.example.ecommerceapp.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        productsAdapter = ProductsAdapter(object: ProductsAdapter.OnItemClickListener {
            override fun onItemClick(product: Product?) {
                startActivity(intent)
                startActivity(Intent(context, ProductDetails::class.java).apply {
                    putExtra("product", product)
                })
            }

        })
        recyclerView.adapter = productsAdapter
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getProductsObserver().observe(this, object: Observer<List<Product>> {
            override fun onChanged(products: List<Product>?) {
                if (!products.isNullOrEmpty()) {
                    productsAdapter.setUpdateProducts(products)
                    productsAdapter.notifyDataSetChanged()
                }
                else Toast.makeText(this@MainActivity, "error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })

        mainActivityViewModel.getLoadingState().observe(this, object: Observer<Boolean> {
            override fun onChanged(loading: Boolean?) {
                if (loading!!) {
                    shimmer.visibility = View.VISIBLE
                    shimmer.startShimmer()
                } else {
                    shimmer.stopShimmer()
                    shimmer.visibility = View.GONE
                }
            }
        })
        mainActivityViewModel.fetchProductsFromAPI()
    }
}