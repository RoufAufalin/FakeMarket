package com.bangkit.core.domain.repository

import com.bangkit.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<com.bangkit.core.data.Result<List<Product>>>

    fun getFavorite(): Flow<List<Product>>

    fun setFavorite(product: Product, newState: Boolean)

    fun setCart(product: Product, newState: Boolean)

    fun getAllCart(): Flow<List<Product>>

}