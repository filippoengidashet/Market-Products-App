package com.filippoengidashet.marketproductsapp.mvvm.view.products.list

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import com.filippoengidashet.marketproductsapp.R
import com.filippoengidashet.marketproductsapp.common.VerticalListMarginDecorator
import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product
import com.filippoengidashet.marketproductsapp.mvvm.model.states.LoadingState
import com.filippoengidashet.marketproductsapp.mvvm.view.base.BaseActivity
import com.filippoengidashet.marketproductsapp.mvvm.view.products.deltails.ProductDetailActivity
import com.filippoengidashet.marketproductsapp.mvvm.viewmodel.products.ProductsViewModel
import com.filippoengidashet.marketproductsapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProductsActivity : BaseActivity() {

    private lateinit var productsAdapter: ProductListAdapter
    private lateinit var productsRView: RecyclerView

    companion object {

        private const val KEY_SEARCH_QUERY = "key.search.query"
    }

    private var searchQuery = ""

    override fun getContentView() = R.layout.activity_products

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val searchMenu = menu.findItem(R.id.actionSearch)
        val searchView = searchMenu.actionView as SearchView
        searchView.queryHint = "Search product..."
        if(!TextUtils.isEmpty(searchQuery)) {
            searchMenu.expandActionView()
            searchView.setQuery(searchQuery, false)
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchMenu.collapseActionView()
//                searchQuery(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchQuery(newText)
                return true
            }
        })
        return super.onPrepareOptionsMenu(menu)
    }

    private fun searchQuery(query: String) {
        searchQuery = query
        val viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        viewModel.getProductsLiveData().value?.let {
            bindProducts(it, query, true)
        }
    }

    private fun bindProducts(
        it: List<Product>,
        query: String,
        scroll: Boolean = false
    ) {
        productsAdapter.setProducts(Utils.search(query, it))
        if (scroll) {
            productsRView.scrollToPosition(0)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionFilter -> {
                showToast("Not supported yet!")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_SEARCH_QUERY, searchQuery)
        super.onSaveInstanceState(outState)
    }

    override fun onViewReady(state: Bundle?) {
        super.onViewReady(state)
        state?.getString(KEY_SEARCH_QUERY)?.let {
            searchQuery = it
        }

        supportActionBar?.title = getString(R.string.app_name)

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        productsAdapter = ProductListAdapter(layoutInflater) { product ->
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, product.productId)
            }
            startActivity(intent)
        }

        val rootView = findViewById<View>(android.R.id.content)

        productsRView = findViewById<RecyclerView>(R.id.products_rview).apply {
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == SCROLL_STATE_DRAGGING) {
                        Utils.hideKeyboard(rootView)
                    }
                }
            })
            addItemDecoration(VerticalListMarginDecorator(Utils.dpToPx(applicationContext, 8f)))
            layoutManager = LinearLayoutManager(this@ProductsActivity)
            adapter = productsAdapter
        }

        val viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        viewModel.getProductsLiveData().observe(this) { data ->
            bindProducts(data, searchQuery)
        }

        if (state == null || !viewModel.didLoad()) {
            viewModel.loadProducts { loadingState ->
                when (loadingState) {
                    LoadingState.LOADING -> {
                        progressBar?.visibility = View.VISIBLE
                    }
                    LoadingState.DONE -> {
                        progressBar?.visibility = View.GONE
                    }
                    else -> {
                    }
                }
            }
        }
    }
}
