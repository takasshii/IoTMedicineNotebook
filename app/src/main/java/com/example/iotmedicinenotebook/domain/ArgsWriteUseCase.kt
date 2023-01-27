package com.example.iotmedicinenotebook.domain

import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.args.ArgsRepository
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity
import javax.inject.Inject

class ArgsWriteUseCase @Inject constructor(
    private val argsRepository: ArgsRepository
) {
    suspend operator fun invoke(medicine: MedicineEntity): Result<Unit> {
        return argsRepository.writeMedicineResultArgs(medicine)
    }
}