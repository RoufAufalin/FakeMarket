package com.bangkit.core.di

import android.content.Context
import androidx.room.Room
import com.bangkit.core.data.local.room.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): com.bangkit.core.data.local.room.ProductDatabase = Room.databaseBuilder(
        context,
        com.bangkit.core.data.local.room.ProductDatabase::class.java, "product.db"
    ).fallbackToDestructiveMigrationFrom().build()

    @Provides
    fun provideProductDao(database: com.bangkit.core.data.local.room.ProductDatabase) = database.productDao()


}