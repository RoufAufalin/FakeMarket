package com.bangkit.capstoneandroidexpert.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor (private val apiService: ApiService) {

    suspend fun getAllTourism(): Flow<ProductResponse> {
        return flow {
            try {
                val response = apiService.getProduct()
                emit(response)
            } catch (e: Exception) {
                emit(ProductResponse(emptyList()))
            }
        }.flowOn(Dispatchers.IO)
    }
}