package com.filippoengidashet.marketproductsapp.di.details

import com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved.ProductsDataStore
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved.ProductsStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author Filippo 19/08/2021
 */
@Module
@InstallIn(ViewModelComponent::class)
class ProductDetailModule {

    @Provides
    fun provideDataStore(dataStore: ProductsDataStore): ProductsStore {
        return dataStore
    }
}
