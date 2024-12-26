package com.bangkit.capstoneandroidexpert.di

import com.bangkit.core.domain.usecase.ProductInteractor
import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideTourismUseCase(productInteractor: ProductInteractor): ProductUseCase

}


