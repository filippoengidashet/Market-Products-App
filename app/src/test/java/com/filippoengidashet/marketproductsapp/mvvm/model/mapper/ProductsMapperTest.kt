package com.filippoengidashet.marketproductsapp.mvvm.model.mapper

import com.filippoengidashet.marketproductsapp.mvvm.model.repository.api.ProductsResponse
import com.google.gson.Gson
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Filippo 19/08/2021
 */
@RunWith(MockitoJUnitRunner::class)
class ProductsMapperTest : TestCase() {

    private val mapper = ProductsMapper()
    private val gson = Gson()

    public override fun setUp() {
        super.setUp()
    }

    @Test(expected = NullPointerException::class)
    fun test_null_data_parser_exception() {
        val response = gson.fromJson("{\"status\" : \"ok\"}", ProductsResponse::class.java)
        mapper.map(response)
    }

    @Test
    fun test_empty_data() {
        val response =
            gson.fromJson("{\"status\" : \"ok\", \"data\" : [] }", ProductsResponse::class.java)
        val products = mapper.map(response)
        assertTrue(products.isEmpty())
    }

    public override fun tearDown() {}
}
