package com.bangkit.core.utils

import com.bangkit.core.data.local.entity.ProductEntity
import com.bangkit.core.data.remote.ProductResponseItem
import com.bangkit.core.domain.model.Product

object DataMapper {
    fun mapResponsesToEntities(input: List<com.bangkit.core.data.remote.ProductResponseItem>): List<com.bangkit.core.data.local.entity.ProductEntity> {
        val tourismList = ArrayList<com.bangkit.core.data.local.entity.ProductEntity>()
        input.map {
            val tourism = com.bangkit.core.data.local.entity.ProductEntity(
                id = it.id,
                title = it.title,
                price = it.price,
                description = it.description,
                category = it.category,
                image = it.image,
                rating = it.rating.rate,
                rate = it.rating.count,
            )
            tourismList.add(tourism)
        }

        return tourismList
    }

    fun mapEntitiesToDomain(input: List<com.bangkit.core.data.local.entity.ProductEntity>): List<Product> =
        input.map {
            Product(
                id = it.id,
                title = it.title,
                price = it.price,
                description = it.description,
                category = it.category,
                image = it.image,
                rating = it.rating,
                rate = it.rate,
                favorite = it.favorite,
                cart = it.cart
            )
        }

    fun mapDomainToEntity(input: Product) = com.bangkit.core.data.local.entity.ProductEntity(
        id = input.id,
        title = input.title,
        price = input.price,
        description = input.description,
        category = input.category,
        image = input.image,
        rating = input.rating,
        rate = input.rate,
        favorite = input.favorite,
        cart = input.cart
    )
}