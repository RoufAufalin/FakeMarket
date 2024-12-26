package com.bangkit.capstoneandroidexpert.presentation.home

import com.bangkit.core.data.ProductRepository
import com.bangkit.core.domain.model.Product
import com.bangkit.core.domain.repository.IProductRepository
import com.bangkit.core.domain.usecase.ProductInteractor
import com.bangkit.core.domain.usecase.ProductUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductUseCaseTest {

    private lateinit var useCase: ProductUseCase

    @Mock private lateinit var repository: IProductRepository


    private lateinit var dummyProducts: List<Product>


    @Before
    fun setup(){
        useCase = ProductInteractor(repository)
        dummyProducts = listOf(
            Product(
                id = 1,
                title = "Wireless Mouse",
                price = 20.99,
                description = "A reliable wireless mouse with ergonomic design.",
                category = "Electronics",
                image = "https://example.com/images/wireless_mouse.jpg",
                rating = 4.5,
                rate = 120,
                favorite = true,
                cart = false
            ),
            Product(
                id = 2,
                title = "Bluetooth Speaker",
                price = 45.50,
                description = "Portable Bluetooth speaker with high-quality sound.",
                category = "Electronics",
                image = "https://example.com/images/bluetooth_speaker.jpg",
                rating = 4.8,
                rate = 200,
                favorite = false,
                cart = true
            )
        )
    }

    @Test
    fun `getFavorite should return only favorite products`() = runBlockingTest {
        val favoriteProducts = dummyProducts.filter { it.favorite }
        `when`(repository.getFavorite()).thenReturn(flow { emit(favoriteProducts) })

        val result = useCase.getFavorite()

        result.collect { products ->
            assertEquals(favoriteProducts, products)
        }
    }
}
