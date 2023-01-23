package com.example.iotmedicinenotebook.data.firestore

import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity

interface FireStoreRepository {
    suspend fun fetchAllMedicineData(limit : Long): Result<List<Medicine>>
    suspend fun fetchCustomMedicineData(medicine: Medicine): Result<List<Medicine>>
    suspend fun fetchCustomMedicineDataList(medicine: MedicineEntity) : Result<List<Medicine>>
}