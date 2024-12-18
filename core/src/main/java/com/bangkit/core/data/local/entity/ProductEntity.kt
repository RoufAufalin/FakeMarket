package com.bangkit.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product")
data class ProductEntity (
    @PrimaryKey
    var id: Int,

    var title: String,

    var price: Double,

    var description: String,

    var category: String,

    var image: String,

    var rating: Double,

    var rate: Int,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

) : Parcelable