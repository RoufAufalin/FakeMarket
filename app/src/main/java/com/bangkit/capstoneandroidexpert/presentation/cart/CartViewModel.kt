package com.bangkit.capstoneandroidexpert.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(productUseCase: ProductUseCase) : ViewModel() {
    val cartData = productUseCase.getAllCart().asLiveData()
}