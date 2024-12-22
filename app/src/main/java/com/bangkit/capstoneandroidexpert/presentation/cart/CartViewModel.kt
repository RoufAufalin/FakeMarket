package com.bangkit.capstoneandroidexpert.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {
    val favoriteData = productUseCase.getFavorite().asLiveData()
}