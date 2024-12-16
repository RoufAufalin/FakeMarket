package com.bangkit.capstoneandroidexpert.di

import com.bangkit.capstoneandroidexpert.data.ProductRepository
import com.bangkit.capstoneandroidexpert.domain.repository.IProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(productRepository: ProductRepository): IProductRepository

}