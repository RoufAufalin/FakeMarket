package com.bangkit.chatfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bangkit.core.domain.usecase.ProductUseCase
import javax.inject.Inject

class CartViewModel (private val useCase: ProductUseCase): ViewModel() {
    val fav = useCase.getFavorite().asLiveData()
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