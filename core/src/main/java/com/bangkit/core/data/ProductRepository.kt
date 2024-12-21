package com.bangkit.core.data

import android.util.Log
import com.bangkit.core.data.local.LocalDataSource
import com.bangkit.core.data.remote.ApiResponse
import com.bangkit.core.data.remote.NetworkBoundResource
import com.bangkit.core.data.remote.ProductResponseItem
import com.bangkit.core.data.remote.RemoteDataSource
import com.bangkit.core.domain.model.Product
import com.bangkit.core.domain.repository.IProductRepository
import com.bangkit.core.utils.AppExecutors
import com.bangkit.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IProductRepository {

    override fun getProducts(): Flow<Result<List<Product>>> {
        return object : NetworkBoundResource<List<Product>, List<ProductResponseItem>>() {
            override fun loadFromDB(): Flow<List<Product>> {
                return localDataSource.getAllProduct().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponseItem>>> {
                return remoteDataSource.getProducts()
            }

            override suspend fun saveCallResult(data: List<ProductResponseItem>) {
                val productList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertProduct(productList)
            }

            override fun shouldFetch(data: List<Product>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun getFavorite(): Flow<List<Product>> {
        return localDataSource.getFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(product: Product, newState: Boolean) {
        val productEntity = DataMapper.mapDomainToEntity(product)

        appExecutors.diskIO().execute{
            localDataSource.setFavorite(productEntity, newState)

        }
    }

    override fun setCart(product: Product, newState: Boolean) {
        val productEntity = DataMapper.mapDomainToEntity(product)
        appExecutors.diskIO().execute {
            localDataSource.setCart(productEntity, newState)
        }
    }

    override fun getAllCart(): Flow<List<Product>> {
        return localDataSource.getAllCart().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }


}