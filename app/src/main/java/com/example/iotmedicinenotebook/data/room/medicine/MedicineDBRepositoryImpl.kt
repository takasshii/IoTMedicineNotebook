package com.example.iotmedicinenotebook.data.room.medicine

import com.example.iotmedicinenotebook.core.Dispatcher
import com.example.iotmedicinenotebook.core.MedicineDispatcher
import com.example.iotmedicinenotebook.core.domain.Medicine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineDBRepositoryImpl @Inject constructor(
    private val medicineDAO: MedicineDAO,
    @Dispatcher(MedicineDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : MedicineDBRepository {
    override suspend fun insert(medicineList: List<MedicineEntity>) {
        withContext(ioDispatcher) {
            medicineDAO.insert(medicineList)
        }
    }

    override suspend fun getAll(): Result<List<MedicineEntity>> =
        withContext(ioDispatcher) {
            try {
                val result = medicineDAO.getAll()
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }


    override suspend fun getCustomMedicine(name: String): Result<List<MedicineEntity>> =
        withContext(ioDispatcher) {
            try {
                val result = medicineDAO.getCustomMedicine(name)
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }


    override suspend fun getLatestCustomMedicine(name: String): Result<MedicineEntity> =
        withContext(ioDispatcher) {
            try {
                val result = medicineDAO.getLatestCustomMedicine(name)
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }


    override suspend fun getLatestMedicine(): Result<List<MedicineEntity>> =
        withContext(ioDispatcher) {
            try {
                val result = medicineDAO.getLatestMedicine()
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }


    override suspend fun delete(medicine: MedicineEntity) {
        withContext(ioDispatcher) {
            medicineDAO.deleteSelected(medicine)
        }
    }
}