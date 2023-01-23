package com.example.iotmedicinenotebook.data

import com.example.iotmedicinenotebook.core.Medicine

interface FireStoreRepository {
    suspend fun fetchAllMedicineData(limit : Long): Result<List<Medicine>>
}