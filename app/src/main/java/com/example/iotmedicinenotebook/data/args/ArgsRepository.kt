package com.example.iotmedicinenotebook.data.args

import com.example.iotmedicinenotebook.core.domain.Medicine

interface ArgsRepository {
    suspend fun writeMedicineResultArgs(medicine: Medicine): Result<Unit>

    suspend fun getMedicineResultArgs(): Result<Medicine>
}
