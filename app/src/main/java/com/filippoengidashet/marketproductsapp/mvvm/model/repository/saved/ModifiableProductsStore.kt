package com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved

import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product

/**
 * @author Filippo 19/08/2021
 */
interface ModifiableProductsStore : ProductsStore {

    fun saveProducts(products: List<Product>)
}