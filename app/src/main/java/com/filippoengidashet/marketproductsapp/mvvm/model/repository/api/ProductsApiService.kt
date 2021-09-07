package com.filippoengidashet.marketproductsapp.mvvm.model.repository.api

import com.filippoengidashet.marketproductsapp.mvvm.model.constants.AppConstants
import retrofit2.http.GET

/**
 * @author Filippo 17/08/2021
 */
interface ProductsApiService {

    @GET(AppConstants.Api.ENDPOINT_PRODUCTS)
    suspend fun getProducts(): ProductsResponse
}
