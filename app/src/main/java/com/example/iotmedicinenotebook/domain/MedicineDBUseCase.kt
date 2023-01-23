package com.example.iotmedicinenotebook.domain

import android.util.Log
import com.example.iotmedicinenotebook.data.firestore.FireStoreRepository
import com.example.iotmedicinenotebook.data.room.medicine.MedicineDBRepository
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity
import javax.inject.Inject

class MedicineDBUseCase @Inject constructor(
    private val medicineDBRepository: MedicineDBRepository,
    private val fireStoreRepository: FireStoreRepository
) {
    suspend operator fun invoke(limit: Long): Result<List<MedicineEntity>> {
        medicineDBRepository.getLatestMedicine().fold(
            onSuccess = { latestMedicine ->
                // 初回起動時
                if (latestMedicine.isEmpty()) {
                    fireStoreRepository.fetchAllMedicineData(limit = limit).fold(
                        onSuccess = { medicineList ->
                            Log.d("UseCaseTest", "fetch remote databse is success ${medicineList}")
                            val medicineEntityList = medicineList.map { remoteMedicine ->
                                fireStoreRepository.fetchCustomMedicineData(remoteMedicine).fold(
                                    onSuccess = { remoteBeforeMedicine ->
                                        if (remoteBeforeMedicine.isEmpty()) {
                                            MedicineEntity(
                                                medicineNumber = remoteMedicine.medicine,
                                                medicineName = "",
                                                rawWeight = remoteMedicine.weight,
                                                difWeight = 0.0,
                                                timeStamp = remoteMedicine.timeStamp,
                                            )
                                        } else {
                                            MedicineEntity(
                                                medicineNumber = remoteMedicine.medicine,
                                                medicineName = "",
                                                rawWeight = remoteMedicine.weight,
                                                difWeight = remoteMedicine.weight - remoteBeforeMedicine.first().weight,
                                                timeStamp = remoteMedicine.timeStamp,
                                            )
                                        }
                                    },
                                    onFailure = {
                                        Log.d("UseCaseTest", "fetch remote databse is failuire ${it}")
                                    }
                                )
                            }
                            Log.d("UseCaseTest", "room latest is Null ${medicineEntityList}")
                            medicineDBRepository.insert(medicineEntityList as List<MedicineEntity>)
                        },
                        onFailure = {},
                    )
                } else {
                    fireStoreRepository.fetchCustomMedicineDataList(latestMedicine.first()).fold(
                        onSuccess = {
                            val medicineEntityList = it.map { remoteMedicine ->
                                fireStoreRepository.fetchCustomMedicineData(remoteMedicine).fold(
                                    onSuccess = { remoteBeforeMedicine ->
                                        if (remoteBeforeMedicine.isEmpty()) {
                                            MedicineEntity(
                                                medicineNumber = remoteMedicine.medicine,
                                                // ここで名前を問い合わせる
                                                medicineName = "",
                                                rawWeight = remoteMedicine.weight,
                                                difWeight = 0.0,
                                                timeStamp = remoteMedicine.timeStamp,
                                            )
                                        } else {
                                            MedicineEntity(
                                                medicineNumber = remoteMedicine.medicine,
                                                // ここで名前を問い合わせる
                                                medicineName = "",
                                                rawWeight = remoteMedicine.weight,
                                                difWeight = remoteMedicine.weight - remoteBeforeMedicine.first().weight,
                                                timeStamp = remoteMedicine.timeStamp,
                                            )
                                        }
                                    },
                                    onFailure = {}
                                )
                            }
                            Log.d("UseCaseTest", "room latest is NotNull ${medicineEntityList}")
                            medicineDBRepository.insert(medicineEntityList as List<MedicineEntity>)
                        },
                        onFailure = {},
                    )
                }
            },
            onFailure = {},
        )
        return medicineDBRepository.getAll()
    }
}