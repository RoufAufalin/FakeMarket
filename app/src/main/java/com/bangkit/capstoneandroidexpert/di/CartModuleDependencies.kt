package com.bangkit.core.di

import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CartModuleDependencies {
    fun provideUseCase(): ProductUseCase
}