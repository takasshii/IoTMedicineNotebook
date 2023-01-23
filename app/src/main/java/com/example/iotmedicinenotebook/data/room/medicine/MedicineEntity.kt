package com.example.iotmedicinenotebook.data.room.medicine

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "medicine")
data class MedicineEntity(
    val medicineNumber: String,
    val medicineName : String,
    val rawWeight : Double,
    val difWeight : Double,
    @PrimaryKey
    val timeStamp: Date,
)