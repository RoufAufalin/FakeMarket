package com.bangkit.capstoneandroidexpert.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(productUseCase: ProductUseCase) : ViewModel() {

    var product = productUseCase.getProducts().asLiveData()
}