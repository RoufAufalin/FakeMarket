package com.bangkit.core.data.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RemoteDataSource @Inject constructor (private val apiService: ApiService) {

    fun getProducts(): Flow<ApiResponse<List<ProductResponseItem>>> = flow {

        try {
            val response = apiService.getProduct()
            Log.d("Data", "$response")
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.d("Error", "$e")
        }

    }.flowOn(Dispatchers.IO)
}