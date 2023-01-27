package com.example.iotmedicinenotebook.ui.detail

import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity
import java.util.*

data class DetailUiState(
    val medicine: MedicineEntity,
    val error: String,
    val proceeding: Boolean,
) {
    companion object {
        val INITIAL = DetailUiState(
            medicine = MedicineEntity(
                id = 0,
                medicineNumber = "測定エラーが発生しました。",
                medicineName = "未登録です。",
                rawWeight = 0.0,
                difWeight = 0.0,
                timeStamp = Date(),
            ),
            error = "",
            proceeding = false,
        )
    }
}