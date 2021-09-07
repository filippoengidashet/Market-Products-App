package com.filippoengidashet.marketproductsapp.mvvm.model.repository

import com.filippoengidashet.marketproductsapp.mvvm.model.mapper.ProductsMapper
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.api.ApiProductsSource
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved.ModifiableProductsStore
import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Filippo 17/08/2021
 */
class ProductsRepository @Inject constructor(
    private val apiDataSource: ApiProductsSource,
    private val dataStore: ModifiableProductsStore,
    private val mapper: ProductsMapper
) {

    suspend fun getProducts(): Flow<List<Product>> {
        return flow {

            //emit data from database
            val savedProducts = withContext(Dispatchers.IO) {
                dataStore.getAllProducts()
            }
            emit(savedProducts)

            try {
                //fetch data from API
                val productList = withContext(Dispatchers.IO) {
                    val response = apiDataSource.getProducts()
                    println("Products fetched!")
                    //map data / transform
                    val products = mapper.map(response)
                    //save into database
                    dataStore.saveProducts(products)
                    return@withContext products
                }
                emit(productList)
            } catch (e: Exception) {
                println(e.message)
                println("Failed to fetch products!")
            } finally {
                println("Done!")
            }
        }
    }
}
