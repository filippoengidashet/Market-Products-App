package com.filippoengidashet.marketproductsapp.mvvm.model.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

/**
 * @author Filippo 20/08/2021
 */
@RunWith(AndroidJUnit4::class)
class ProductsDaoTest {

    companion object {
        private const val TEST_ID = "123"
        private const val TEST_TITLE = "special-product"
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun test_insert_product() {
        val product = createProduct(TEST_ID)
        val productsDao = appDatabase.productsDao()
        productsDao.insert(product)
        //test product count
        Assert.assertEquals(productsDao.getAll().size, 1)
        //test product content
        Assert.assertEquals(productsDao.getAll()[0].productId, TEST_ID)
    }

    @Test
    fun test_find_product() {

        val product1 = createProduct("id-123")
        val product2 = createProduct(TEST_ID, TEST_TITLE)
        val product3 = createProduct("id-124")
        val product4 = createProduct("id-125")
        val product5 = createProduct("id-126")

        val productsDao = appDatabase.productsDao()
        productsDao.insert(product1)
        productsDao.insert(product2)
        productsDao.insert(product3)
        productsDao.insert(product4)
        productsDao.insert(product5)

        val foundProduct = productsDao.findProduct(id = TEST_ID)

        //check not null
        Assert.assertNotNull(foundProduct)

        //test product name
        Assert.assertEquals(foundProduct?.title, TEST_TITLE)
    }

    @Test
    fun test_delete_all_products() {
        val productsDao = appDatabase.productsDao()
        for (i in 0 until 10) {
            val product = createProduct(TEST_ID)
            productsDao.insert(product)
        }
        //test product count
        Assert.assertEquals(productsDao.getAll().size, 10)

        //clear products from database
        productsDao.clearAll()

        //check there are no products in the database
        Assert.assertTrue(productsDao.getAll().isEmpty())
    }

    private fun createProduct(id: String, title: String = "test_title"): Product {
        return Product.Builder()
            .withProductId(id)
            .withTitle(title)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }
}
