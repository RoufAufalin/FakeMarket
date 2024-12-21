package com.bangkit.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    var id: Int,
    var title: String,
    var price: Double,
    var description: String,
    var category: String,
    var image: String,
    var rating: Double,
    var rate: Int,
    var favorite: Boolean,
    var cart: Boolean
) : Parcelable
