package com.bangkit.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.bangkit.core.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAllProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE favorite = 1")
    fun getAllFavorite(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<ProductEntity>)

    @Update
    fun setFavorite(product: ProductEntity)

    @Update
    fun setCart(product: ProductEntity)

    @Query("SELECT * FROM product WHERE cart = 1")
    fun getAllCart(): Flow<List<ProductEntity>>


}