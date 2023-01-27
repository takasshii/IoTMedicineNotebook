package com.example.iotmedicinenotebook.ui.home

import com.example.iotmedicinenotebook.core.domain.Medicine

sealed interface MedicineEvent {
    data class MedicineList(
        val medicineList: List<Medicine>
    ) : MedicineEvent

    data class UnknownExpectedError(
        val errorMessage: String,
    ) : MedicineEvent
}