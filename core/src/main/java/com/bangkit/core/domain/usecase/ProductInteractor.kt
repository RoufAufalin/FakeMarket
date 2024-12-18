package com.bangkit.core.domain.usecase

import com.bangkit.core.domain.model.Product
import com.bangkit.core.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val productRepository: IProductRepository) :
    ProductUseCase {
    override fun getProducts() = productRepository.getProducts()

    override fun getFavorite() = productRepository.getFavorite()

    override fun setFavorite(product: Product, newState: Boolean) = productRepository.setFavorite(product, newState)

}