package com.bangkit.core.di

import com.bangkit.core.data.ProductRepository
import com.bangkit.core.data.local.LocalDataSource
import com.bangkit.core.data.remote.RemoteDataSource
import com.bangkit.core.domain.repository.IProductRepository
import com.bangkit.core.utils.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, LocalModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remote: RemoteDataSource,
        local: LocalDataSource,
        executors: AppExecutors
    ): IProductRepository =
        ProductRepository(remote, local, executors)

}