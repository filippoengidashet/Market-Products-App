package com.filippoengidashet.marketproductsapp.di.products

import com.filippoengidashet.marketproductsapp.mvvm.model.repository.api.ProductsApiService
import com.filippoengidashet.marketproductsapp.mvvm.model.constants.AppConstants
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.api.ApiProductsSource
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.api.ApiProductsSourceImpl
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved.ModifiableProductsStore
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved.ProductsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Filippo 19/08/2021
 */
@Module
@InstallIn(ViewModelComponent::class)
class ProductsModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ProductsApiService::class.java)

    @Provides
    fun provideApiDataSource(source: ApiProductsSourceImpl): ApiProductsSource {
        return source
    }

    @Provides
    fun provideModifiableDataStore(dataStore: ProductsDataStore): ModifiableProductsStore {
        return dataStore
    }
}
