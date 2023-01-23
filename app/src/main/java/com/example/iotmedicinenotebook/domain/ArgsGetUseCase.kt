package com.example.iotmedicinenotebook.domain

import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.args.ArgsRepository
import javax.inject.Inject

class ArgsGetUseCase @Inject constructor(
    private val argsRepository: ArgsRepository
) {
    suspend operator fun invoke(): Result<Medicine> {
        return argsRepository.getMedicineResultArgs()
    }
}