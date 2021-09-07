package com.filippoengidashet.marketproductsapp.di.app

import android.content.Context
import androidx.room.Room
import com.filippoengidashet.marketproductsapp.mvvm.model.constants.AppConstants
import com.filippoengidashet.marketproductsapp.mvvm.model.database.AppDatabase
import com.filippoengidashet.marketproductsapp.mvvm.model.database.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Filippo 19/08/2021
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.Database.DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsDao(database: AppDatabase): ProductsDao {
        return database.productsDao()
    }
}
