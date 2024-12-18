package com.bangkit.core.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProduct(): List<ProductResponseItem>
}