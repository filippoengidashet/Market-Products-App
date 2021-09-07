package com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved

import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product

/**
 * @author Filippo 17/08/2021
 */
interface ProductsStore {

    fun getProductById(id: String): Product?

    fun getAllProducts(): List<Product>
}