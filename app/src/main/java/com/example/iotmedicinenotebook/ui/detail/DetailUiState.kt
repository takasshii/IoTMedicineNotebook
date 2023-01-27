package com.example.iotmedicinenotebook.ui.detail

import com.example.iotmedicinenotebook.core.domain.Medicine

data class DetailUiState(
    val medicine: Medicine,
    val error: String,
    val proceeding: Boolean,
) {
    companion object {
        val INITIAL = DetailUiState(
            medicine = Medicine(),
            error = "",
            proceeding = false,
        )
    }
}