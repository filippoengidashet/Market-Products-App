package com.filippoengidashet.marketproductsapp.mvvm.view.products.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.filippoengidashet.marketproductsapp.R
import com.filippoengidashet.marketproductsapp.common.ImageLoaderHelper
import com.filippoengidashet.marketproductsapp.common.SimpleDiffUtilCallback
import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product

/**
 * @author Filippo 19/08/2021
 */
class ProductListAdapter(
    private val inflater: LayoutInflater,
    private val actionItemClick: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private val products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            inflater.inflate(R.layout.item_product_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    fun setProducts(_products: List<Product>) {
        val oldList = ArrayList(products)
        products.clear()
        products.addAll(_products)
        val diffCallback = object : SimpleDiffUtilCallback<Product>(oldList, _products) {

            override fun isSameItem(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

            override fun isSameContent(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageLogo = itemView.findViewById<ImageView>(R.id.image_logo)
        private val textProductTitle = itemView.findViewById<TextView>(R.id.text_title)
        private val textProductPrice = itemView.findViewById<TextView>(R.id.text_price)
        private val textProductCreationDate =
            itemView.findViewById<TextView>(R.id.text_creation_date)
        private val imageAgeRestriction =
            itemView.findViewById<ImageView>(R.id.image_age_restriction)
        private val textProductDescription = itemView.findViewById<TextView>(
            R.id.text_product_description
        )

        init {
            itemView.setOnClickListener {
                actionItemClick(products[adapterPosition])
            }
        }

        fun bind(product: Product) {
            with(product) {
                textProductTitle.text = title
                textProductDescription.text = description
                textProductPrice.text = "£%s".format(price)
                textProductCreationDate.text = date
            }
            imageAgeRestriction.visibility = if (product.ageRestricted) {
                View.VISIBLE
            } else View.INVISIBLE
            ImageLoaderHelper.loadImage(product.imageUrl, imageLogo)
        }
    }
}