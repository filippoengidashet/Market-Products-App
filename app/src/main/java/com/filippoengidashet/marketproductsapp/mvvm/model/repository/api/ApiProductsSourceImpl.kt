package com.filippoengidashet.marketproductsapp.mvvm.model.repository.api

import javax.inject.Inject

/**
 * @author Filippo 19/08/2021
 */
class ApiProductsSourceImpl @Inject constructor(
    private val apiService: ProductsApiService
) : ApiProductsSource {

    override suspend fun getProducts(): ProductsResponse {
        return apiService.getProducts()
    }
}