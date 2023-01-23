package com.example.iotmedicinenotebook.data.di

import com.example.iotmedicinenotebook.data.FireStoreRepository
import com.example.iotmedicinenotebook.data.FireStoreRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FireStoreModule {
    @Provides
    @Singleton
    fun provideFirebase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FireStoreRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindFireStoreRepositoryImpl(
        impl: FireStoreRepositoryImpl
    ): FireStoreRepository
}