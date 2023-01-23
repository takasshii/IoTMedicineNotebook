package com.example.iotmedicinenotebook.ui.home

import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity

sealed interface MedicineEvent {
    data class MedicineList(
        val medicineList: List<MedicineEntity>
    ) : MedicineEvent

    data class UnknownExpectedError(
        val errorMessage: String,
    ) : MedicineEvent
}