package com.example.iotmedicinenotebook.ui.home

import com.example.iotmedicinenotebook.core.domain.Medicine

data class MedicineUiState(
    val medicineList: List<Medicine>,
    val error: String,
    val events: List<Event>,
    val proceeding: Boolean,
) {
    companion object {
        val INITIAL = MedicineUiState(
            medicineList = emptyList(),
            error = "",
            events = emptyList(),
            proceeding = false
        )
    }

    sealed interface Event {
        object Success : Event
        data class Error(val message: String) : Event
        data class NextPage(val medicineResult: Medicine) : Event
    }
}
