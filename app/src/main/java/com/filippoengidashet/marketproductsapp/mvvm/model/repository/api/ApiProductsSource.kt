package com.filippoengidashet.marketproductsapp.mvvm.model.repository.api

/**
 * @author Filippo 17/08/2021
 */
interface ApiProductsSource {

    suspend fun getProducts(): ProductsResponse
}