package com.example.iotmedicinenotebook.data.args

import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity

interface ArgsRepository {
    suspend fun writeMedicineResultArgs(medicine: MedicineEntity): Result<Unit>

    suspend fun getMedicineResultArgs(): Result<MedicineEntity>
}
