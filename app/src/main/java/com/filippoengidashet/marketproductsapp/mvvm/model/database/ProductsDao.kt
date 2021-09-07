package com.filippoengidashet.marketproductsapp.mvvm.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Filippo 17/08/2021
 */
@Dao
interface ProductsDao {

    companion object {
        const val TABLE_PRODUCTS = "products_table"
        const val TABLE_CATEGORY = "products_table"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Query("SELECT * FROM $TABLE_PRODUCTS WHERE product_id LIKE :id")
    fun findProduct(id: String): Product?

    @Query("SELECT * FROM $TABLE_PRODUCTS")
    fun getAll(): List<Product>

    @Query("DELETE FROM $TABLE_PRODUCTS")
    fun clearAll()
}
