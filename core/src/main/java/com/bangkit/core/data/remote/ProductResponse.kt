package com.bangkit.core.data.remote

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("ProductResponse")
	val productResponse: List<ProductResponseItem>
)

data class ProductResponseItem(

    @field:SerializedName("image")
	val image: String,

    @field:SerializedName("price")
	val price: Double,

    @field:SerializedName("rating")
	val rating: Rating,

    @field:SerializedName("description")
	val description: String,

    @field:SerializedName("id")
	val id: Int,

    @field:SerializedName("title")
	val title: String,

    @field:SerializedName("category")
	val category: String
)

data class Rating(

	@field:SerializedName("rate")
	val rate: Double,

	@field:SerializedName("count")
	val count: Int
)
