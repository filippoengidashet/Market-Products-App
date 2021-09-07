package com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved

import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product
import com.filippoengidashet.marketproductsapp.mvvm.model.database.ProductsDao
import javax.inject.Inject

/**
 * @author Filippo 17/08/2021
 */
class ProductsDataStore @Inject constructor(
    private val dao: ProductsDao
) : ModifiableProductsStore {

    override fun getProductById(id: String): Product? {
        return dao.findProduct(id)
    }

    override fun getAllProducts(): List<Product> {
        return dao.getAll()
    }

    override fun saveProducts(products: List<Product>) {
        if (products.isNotEmpty()) {
            dao.clearAll()
            for (product in products) {
                dao.insert(product)
            }
        }
    }
}