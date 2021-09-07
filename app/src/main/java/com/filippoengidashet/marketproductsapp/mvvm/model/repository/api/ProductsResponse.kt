package com.filippoengidashet.marketproductsapp.mvvm.model.repository.api

/**
 * @author Filippo 19/08/2021
 */
data class ProductsResponse(
    val status: String,
    val meta: MetaResponse?,
    val data: List<DataResponse>,
)

data class MetaResponse(
    val max_products_per_box: String,
    val offset: Int,
    val limit: Int,
    val count: Int,
    val total: Int,
)

data class DataResponse(
    val id: String,
    val sku: String?,
    val title: String?,
    val description: String?,
    val list_price: String?,
    val is_vatable: Boolean,
    val is_for_sale: Boolean,
    val age_restricted: Boolean?,
    val box_limit: Int,
    val always_on_menu: Boolean,
    val volume: Int,
    val zone: String?,
    val created_at: String,
    val categories: List<CategoryResponse>,
    val attributes: List<AttributeResponse>,
    val tags: List<String>,
    val images: Map<String, ImageResponse>?,
)

data class CategoryResponse(
    val id: String,
    val title: String,
    val box_limit: Int,
    val is_default: Boolean,
    val recently_added: Boolean,
    val hidden: Boolean,
    val pivot: Pivot,
)

data class Pivot(
    val created_at: String,
)

data class AttributeResponse(
    val id: String,
    val title: String,
    val unit: String,
    val value: String,
)

data class ImageResponse(
    val src: String,
    val url: String,
    val width: String,
)
