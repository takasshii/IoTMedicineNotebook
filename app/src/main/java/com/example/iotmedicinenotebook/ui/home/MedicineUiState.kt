package com.example.iotmedicinenotebook.ui.home

import com.example.iotmedicinenotebook.core.Medicine

data class MedicineUiState(
    val medicineList: List<Medicine>,
    val error: String,
    val events: List<Event>,
) {
    companion object {
        val INITIAL = MedicineUiState(
            medicineList = emptyList(),
            error = "",
            events = emptyList(),
        )
    }

    sealed interface Event {
        object Success : Event
        data class Error(val message: String) : Event
        data class NextPage(val medicineResult: Medicine) : Event
    }
}
