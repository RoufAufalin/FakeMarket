package com.bangkit.core.data.local

import com.bangkit.core.data.local.entity.ProductEntity
import com.bangkit.core.data.local.room.ProductDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val productDao: ProductDao){
    fun getAllProduct() = productDao.getAllProduct()

    fun getFavorite() = productDao.getAllFavorite()

    suspend fun insertProduct(product: List<ProductEntity>) = productDao.insertProduct(product)

    fun setFavorite(product: ProductEntity, newState: Boolean) {
        product.favorite = newState
        productDao.setFavorite(product)
    }

}