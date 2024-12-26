package com.bangkit.capstoneandroidexpert.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bangkit.core.domain.model.Product
import com.bangkit.core.domain.usecase.ProductUseCase
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import com.bangkit.core.data.Result
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val productUseCase = mockk<ProductUseCase>(relaxed = true)
    private val observer = mockk<Observer<Result<List<Product>>>>(relaxed = true)

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { productUseCase.getProducts() } returns flow {
            emit(Result.Loading)
            emit(Result.Success(listOf(Product(id = 1, name = "Test Product"))))
        }

        homeViewModel = HomeViewModel(productUseCase)
        homeViewModel.product.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        homeViewModel.product.removeObserver(observer)
    }

    @Test
    fun `verify product LiveData emits loading and success states`() = runTest {

        advanceUntilIdle()

        verify { observer.onChanged(Result.Loading) }

        verify { observer.onChanged(Result.Success(listOf(Product(id = 1, name = "Test Product")))) }

        confirmVerified(observer)
    }
}