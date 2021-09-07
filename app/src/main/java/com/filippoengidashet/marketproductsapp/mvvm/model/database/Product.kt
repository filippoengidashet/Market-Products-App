package com.filippoengidashet.marketproductsapp.mvvm.model.database

import androidx.room.*

/**
 * @author Filippo 17/08/2021
 */
@Entity(tableName = ProductsDao.TABLE_PRODUCTS)
data class Product(
    @ColumnInfo(name = "product_id") val productId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "ageRestricted") val ageRestricted: Boolean,
    @ColumnInfo(name = "sku") val sku: String,
    @ColumnInfo(name = "zone") val zone: String,
    @ColumnInfo(name = "creation_date") val date: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    class Builder {

        private var productId: String = ""
        private var title: String = ""
        private var description: String = ""
        private var price: String = ""
        private var imageUrl: String = ""
        private var ageRestricted: Boolean = false
        private var sku: String = ""
        private var zone: String = ""
        private var date: String = ""

        fun withProductId(_id: String) = apply { productId = _id }
        fun withTitle(_title: String) = apply { title = _title }
        fun withDescription(_description: String) = apply { description = _description }
        fun withPrice(_price: String) = apply { price = _price }
        fun withImageUrl(_imageUrl: String) = apply { imageUrl = _imageUrl }
        fun withAgeRestricted(_ageRestricted: Boolean) = apply { ageRestricted = _ageRestricted }
        fun withSku(_sku: String) = apply { sku = _sku }
        fun withZone(_zone: String) = apply { zone = _zone }
        fun withCreationDate(_date: String) = apply { date = _date }

        fun build(): Product {
            return Product(
                productId, title, description, price, imageUrl, ageRestricted, sku, zone, date
            )
        }
    }
}
