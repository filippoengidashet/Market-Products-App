package com.filippoengidashet.marketproductsapp.mvvm.model.mapper

import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.api.ProductsResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author Filippo 17/08/2021
 */
class ProductsMapper @Inject constructor() {

    companion object {
        private const val PDF = "yyyy-MM-dd'T'HH:mm:ssZ"
        private const val DF = "yyyy-MM-dd HH:mm"
    }

    fun map(from: ProductsResponse): List<Product> {
        val dataList = mutableListOf<Product>()
        val pdf = SimpleDateFormat(PDF, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val df = SimpleDateFormat(DF, Locale.getDefault())

        for (p in from.data) {

            val imageURL = p.images?.values?.first()?.src ?: ""

            val date = pdf.parse(p.created_at)?.let { df.format(it) } ?: ""

            val product = Product.Builder()
                .withProductId(p.id)
                .withTitle(p.title ?: "")
                .withDescription(p.description ?: "")
                .withPrice(p.list_price ?: "0.00")
                .withAgeRestricted(p.age_restricted ?: false)
                .withImageUrl(imageURL)
                .withSku(p.sku ?: "")
                .withZone(p.zone ?: "")
                .withCreationDate(date)
                .build()

            dataList.add(product)
        }
        return dataList
    }
}
