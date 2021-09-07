package com.filippoengidashet.marketproductsapp.mvvm.view.products.deltails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.filippoengidashet.marketproductsapp.R
import com.filippoengidashet.marketproductsapp.common.ImageLoaderHelper
import com.filippoengidashet.marketproductsapp.mvvm.view.base.BaseActivity
import com.filippoengidashet.marketproductsapp.mvvm.viewmodel.details.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : BaseActivity() {

    override fun getContentView() = R.layout.activity_product_detail

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewReady(state: Bundle?) {
        super.onViewReady(state)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.title_product_detail)
        }

        val imageLogo = findViewById<ImageView>(R.id.image_logo)
        val textProductTitle = findViewById<TextView>(R.id.text_title)
        val textProductSku = findViewById<TextView>(R.id.text_sku)
        val imageAgeRestriction = findViewById<ImageView>(R.id.image_age_restriction).apply { 
            setOnClickListener { 
                showToast(context.getString(R.string.text_age_restricted))
            }
        }
        val textProductDescription = findViewById<TextView>(R.id.text_product_description)
        val textProductPrice = findViewById<TextView>(R.id.text_price)

        val viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        viewModel.getProductLiveData().observe(this) { product ->
            textProductTitle.text = product.title
            textProductSku.text = product.sku
            textProductDescription.text = product.description
            textProductPrice.text = "£%s".format(product.price)
            imageAgeRestriction.visibility = if (product.ageRestricted) {
                View.VISIBLE
            } else View.INVISIBLE
//            imageAgeRestriction.setImageResource(
//                if (product.ageRestricted) {
//                    R.drawable.ic_check_circle_24
//                } else R.drawable.ic_cancel_24
//            )
            ImageLoaderHelper.loadImage(product.imageUrl, imageLogo)
        }

        if (state == null) {
            intent.getStringExtra(EXTRA_PRODUCT_ID)?.let { productId ->
                viewModel.loadProductById(productId)
            }
        }
    }

    companion object {

        const val EXTRA_PRODUCT_ID = "extra.product.id"
    }
}