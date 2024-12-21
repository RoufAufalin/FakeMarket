package com.bangkit.core.di

import com.bangkit.core.domain.usecase.ProductInteractor
import com.bangkit.core.domain.usecase.ProductUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideTourismUseCase(productInteractor: ProductInteractor): ProductUseCase

}


