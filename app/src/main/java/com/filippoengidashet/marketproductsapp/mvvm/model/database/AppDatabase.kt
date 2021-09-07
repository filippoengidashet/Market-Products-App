package com.filippoengidashet.marketproductsapp.mvvm.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filippoengidashet.marketproductsapp.mvvm.model.constants.AppConstants

/**
 * @author Filippo 17/08/2021
 */
@Database(entities = [Product::class], version = AppConstants.Database.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao
}
