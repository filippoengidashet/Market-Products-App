package com.filippoengidashet.marketproductsapp.mvvm.viewmodel.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product
import com.filippoengidashet.marketproductsapp.mvvm.model.repository.saved.ProductsStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * @author Filippo 20/08/2021
 */
class ProductDetailViewModelTest {

    companion object {
        private const val TEST_ID = "test_product_id"
    }

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @Mock
    private lateinit var store: ProductsStore
    private var viewModel: ProductDetailViewModel? = null

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = ProductDetailViewModel(store)
    }

    @Test
    fun test_loadProductById() {

        val product = Product.Builder()
            .withProductId(TEST_ID)
            .build()

        `when`(store.getProductById(TEST_ID)).thenReturn(product)

        dispatcher.runBlockingTest {
            viewModel?.getProductLiveData()?.observeForever {
                val x = 0
                println("----TEST-----")
                println("Product $it")
                println("----TEST-----")
            }
            viewModel?.loadProductById(TEST_ID)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel = null
    }
}