package com.filippoengidashet.marketproductsapp.mvvm.model.constants

/**
 * @author Filippo 17/08/2021
 */
object AppConstants {

    object Database {

        const val DB_NAME = "market-products.db"
        const val DB_VERSION = 0x001
    }

    object Api {

        const val BASE_URL = "https://api.gousto.co.uk"
        const val ENDPOINT_PRODUCTS =
            "/products/v2/products?includes[]=categories&includes[]=attributes&image_sizes[]=300"
    }
}
