package com.example.iotmedicinenotebook.ui.home.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.core.domain.toDateFormat
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity


@Composable
fun MedicineScreen(
    nextPage: (medicine: MedicineEntity) -> Unit,
    medicineList: List<MedicineEntity>,
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
            LazyColumn(modifier = modifier) {
                items(medicineList) { medicine ->
                    MedicineButton(medicine, nextPage)
                }
            }
        }
    }
}

@Composable
fun MedicineButton(medicine: MedicineEntity, nextPage: (medicine: MedicineEntity) -> Unit) {
    Box(
        modifier = Modifier
            .clickable {
                nextPage(medicine)
            }
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
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
                    medicine.difWeight.toString()
                },
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

    }
}