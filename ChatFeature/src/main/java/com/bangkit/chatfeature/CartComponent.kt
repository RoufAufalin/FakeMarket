package com.bangkit.chatfeature

import android.content.Context
import com.bangkit.capstoneandroidexpert.di.CartModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CartModuleDependencies::class]
)
interface CartComponent {
    fun inject(chatActivity: ChatActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: CartModuleDependencies): Builder
        fun build(): CartComponent
    }
}