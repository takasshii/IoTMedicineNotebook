package com.example.iotmedicinenotebook.data.di

import android.content.Context
import androidx.room.Room
import com.example.iotmedicinenotebook.data.room.medicine.MedicineDAO
import com.example.iotmedicinenotebook.data.room.medicine.MedicineDBRepository
import com.example.iotmedicinenotebook.data.room.medicine.MedicineDBRepositoryImpl
import com.example.iotmedicinenotebook.data.room.medicine.MedicineDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedicineDBModule {
    @Provides
    @Singleton
    fun provideHistoryDatabase(
        @ApplicationContext context: Context
    ): MedicineDatabase {
        return Room.databaseBuilder(
            context,
            MedicineDatabase::class.java,
            "medicine.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMedicineDAO(db: MedicineDatabase): MedicineDAO {
        return db.medicineDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class MedicineDBRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindHistoryRepository(
        impl: MedicineDBRepositoryImpl
    ): MedicineDBRepository
}