package com.example.iotmedicinenotebook.data.room.medicine

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.iotmedicinenotebook.data.room.Converters

// Database class after the version update.
@Database(
    version = 1,
    entities = [MedicineEntity::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDAO
}