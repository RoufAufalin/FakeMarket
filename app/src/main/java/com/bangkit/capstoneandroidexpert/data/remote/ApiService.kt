package com.bangkit.capstoneandroidexpert.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProduct(): ProductResponse
}