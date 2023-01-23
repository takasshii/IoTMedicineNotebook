package com.example.iotmedicinenotebook.data.room.medicine

import com.example.iotmedicinenotebook.core.domain.Medicine

interface MedicineDBRepository {
    suspend fun insert(medicineList: List<MedicineEntity>)
    suspend fun getAll(): Result<List<MedicineEntity>>
    suspend fun getCustomMedicine(name: String): Result<List<MedicineEntity>>
    suspend fun getLatestCustomMedicine(name: String): Result<MedicineEntity>
    suspend fun getLatestMedicine(): Result<List<MedicineEntity>>
    suspend fun delete(medicine: MedicineEntity)
}