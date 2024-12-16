package com.bangkit.capstoneandroidexpert.di

import com.bangkit.capstoneandroidexpert.domain.usecase.ProductInteractor
import com.bangkit.capstoneandroidexpert.domain.usecase.ProductUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideTourismUseCase(productInteractor: ProductInteractor): ProductUseCase

}