package com.bangkit.capstoneandroidexpert.presentation.detail

import androidx.lifecycle.ViewModel
import com.bangkit.core.domain.model.Product
import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {

    fun setFavorite(product: Product, newState: Boolean) {
        productUseCase.setFavorite(product, newState)
    }

    fun setCart(product: Product, newState: Boolean) {
        productUseCase.setCart(product, newState)
    }

}