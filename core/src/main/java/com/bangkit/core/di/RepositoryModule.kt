package com.bangkit.core.di

import com.bangkit.core.data.ProductRepository
import com.bangkit.core.domain.repository.IProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(productRepository: com.bangkit.core.data.ProductRepository): IProductRepository


}