package com.bangkit.capstoneandroidexpert.domain.usecase

import com.bangkit.capstoneandroidexpert.data.remote.ProductResponseItem
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getProducts() : Flow<List<ProductResponseItem>>
}