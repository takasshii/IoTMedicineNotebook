package com.example.iotmedicinenotebook.data.firestore

import com.example.iotmedicinenotebook.core.domain.Medicine

interface FireStoreRepository {
    suspend fun fetchAllMedicineData(limit : Long): Result<List<Medicine>>
}