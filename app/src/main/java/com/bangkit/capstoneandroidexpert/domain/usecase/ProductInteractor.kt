package com.bangkit.capstoneandroidexpert.domain.usecase

import com.bangkit.capstoneandroidexpert.domain.repository.IProductRepository
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val productRepository: IProductRepository) : ProductUseCase {

    override fun getProducts() = productRepository.getProducts()
}