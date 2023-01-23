package com.example.iotmedicinenotebook.data.di

import com.example.iotmedicinenotebook.data.args.ArgsRepository
import com.example.iotmedicinenotebook.data.args.ArgsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArgsRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindArgsRepository(
        impl: ArgsRepositoryImpl
    ): ArgsRepository
}