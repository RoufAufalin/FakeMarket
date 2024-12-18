package com.bangkit.core.domain.usecase

import com.bangkit.core.data.remote.ProductResponseItem
import com.bangkit.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import com.bangkit.core.data.Result

interface ProductUseCase {

    fun getProducts() : Flow<com.bangkit.core.data.Result<List<Product>>>

    fun getFavorite() : Flow<List<Product>>

    fun setFavorite(product: Product, newState: Boolean)

}