package com.example.iotmedicinenotebook.domain

import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.firestore.FireStoreRepository
import javax.inject.Inject

class FireStoreUseCase @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) {
    suspend operator fun invoke(limit: Long): Result<List<Medicine>> {
        return fireStoreRepository.fetchAllMedicineData(limit)
    }
}