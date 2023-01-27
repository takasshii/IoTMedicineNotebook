package com.example.iotmedicinenotebook.ui.detail.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iotmedicinenotebook.core.domain.toDateFormat
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity

@Composable
fun DetailScreen(
    medicine: MedicineEntity,
    proceeding: Boolean,
    modifier: Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (proceeding) {
            CircularProgressIndicator()
        } else {
            Column {
                Text(
                    text = medicine.timeStamp.toDateFormat(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = medicine.medicineName.ifBlank {
                        "登録されていない薬です。 ${medicine.medicineNumber}"
                    },
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = if (medicine.difWeight == 0.0) {
                        "登録を行いますか？"
                    } else {
                        medicine.medicineName.toString()
                    },
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "${medicine.rawWeight} g",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = medicine.medicineNumber,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}