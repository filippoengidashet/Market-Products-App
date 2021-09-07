package com.filippoengidashet.marketproductsapp.mvvm.viewmodel.details

import androidx.lifecycle.*
import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved.ProductsStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Filippo 19/08/2021
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val store: ProductsStore
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    fun getProductLiveData(): LiveData<Product> = _product

    fun loadProductById(productId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                store.getProductById(productId)
            }?.also { product ->
                _product.value = product
            }
        }
    }
}
