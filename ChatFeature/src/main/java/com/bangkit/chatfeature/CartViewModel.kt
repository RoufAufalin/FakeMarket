package com.bangkit.chatfeature

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bangkit.capstoneandroidexpert.presentation.favorite.FavoriteViewModel
import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class CartViewModel (private val useCase: ProductUseCase): ViewModel() {
    val cart = useCase.getAllCart().asLiveData()
}

class ViewModelFactory @Inject constructor(
    private val useCase: ProductUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(CartViewModel::class.java) -> CartViewModel(useCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
}