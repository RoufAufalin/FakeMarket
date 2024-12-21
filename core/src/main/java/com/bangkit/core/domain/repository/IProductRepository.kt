package com.bangkit.core.domain.repository

import androidx.lifecycle.LiveData
import com.bangkit.core.data.Result
import com.bangkit.core.data.local.entity.ProductEntity
import com.bangkit.core.data.remote.ProductResponseItem
import com.bangkit.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<com.bangkit.core.data.Result<List<Product>>>

    fun getFavorite(): Flow<List<Product>>

    fun setFavorite(product: Product, newState: Boolean)

    fun setCart(product: Product, newState: Boolean)

    fun getAllCart(): Flow<List<Product>>

}