package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerceapp.R
import com.example.ecommerceapp.dataModel.Product
import kotlinx.android.synthetic.main.activity_products_details.*
import kotlinx.android.synthetic.main.products_list_row.view.*

class ProductsAdapter(
    onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val clickListener: OnItemClickListener = onItemClickListener
    private var products: List<Product>? = null

    fun setUpdateProducts(products: List<Product>) {
        this.products = products
    }

    class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val productListCard = view.productListCard
        val imageView = view.imageView
        val tvTitle = view.tvTitle
        val tvPrice = view.tvPrice
        val tvRating = view.tvRating

        fun bind(product: Product, clickListener: OnItemClickListener) {
            Glide.with(imageView)
                .load(product.image)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
            tvTitle.setText(product.title)
            tvPrice.setText("Price: $" + product.price)
            tvRating.setText("Rating: " + product.rating?.rate + " (" + product.rating?.count + ")")
            productListCard.setOnClickListener(object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    clickListener.onItemClick(product)
                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_list_row, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (!products.isNullOrEmpty())
            return products?.size!!
        else return 0
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products?.get(position)!!, clickListener)
    }


    interface OnItemClickListener {
        fun onItemClick(product: Product?)
    }
}