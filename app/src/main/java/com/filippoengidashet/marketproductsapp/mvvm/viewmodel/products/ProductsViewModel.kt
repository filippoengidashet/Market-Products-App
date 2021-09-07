package com.filippoengidashet.marketproductsapp.mvvm.viewmodel.products

import android.app.Application
import androidx.lifecycle.*
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.ProductsRepository
import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product
import com.filippoengidashet.marketproductsapp.mvvm.model.states.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Filippo 19/08/2021
 */
@HiltViewModel
class ProductsViewModel @Inject constructor(
    application: Application,
    private val repository: ProductsRepository,
) : AndroidViewModel(application) {

    private var loaded: Boolean = false
    private var job: Job? = null
    private val _products = MutableLiveData<List<Product>>()
    fun getProductsLiveData(): LiveData<List<Product>> = _products

    /**
     * This method loads products from cache first and later updated from API data
     */
    fun loadProducts(notifyState: (state: LoadingState) -> Unit) {
        loaded = false
        notifyState(LoadingState.LOADING)
        job?.takeIf { it.isActive }?.cancel()
        job = viewModelScope.launch {
            repository.getProducts().collect { products ->
                _products.value = products
            }
            loaded = true
            notifyState(LoadingState.DONE)
        }
    }

    fun didLoad(): Boolean = loaded
}