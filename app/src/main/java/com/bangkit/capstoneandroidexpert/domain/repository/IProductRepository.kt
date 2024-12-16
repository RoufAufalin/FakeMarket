package com.bangkit.capstoneandroidexpert.domain.repository

import androidx.lifecycle.LiveData
import com.bangkit.capstoneandroidexpert.data.remote.ProductResponseItem
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<List<ProductResponseItem>>
}