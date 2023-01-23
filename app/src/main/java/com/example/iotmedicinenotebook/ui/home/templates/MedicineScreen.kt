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


@Composable
fun MedicineScreen(
    nextPage: (medicine: Medicine) -> Unit,
    medicineList: List<Medicine>,
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
fun MedicineButton(medicine: Medicine, nextPage: (medicine: Medicine) -> Unit) {
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
                text = if (medicine.timeStamp != null) {
                    medicine.timeStamp.toDateFormat()
                } else {
                    "日時情報がありません"
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            medicine.medicine?.let {
                Text(
                    text = if (medicine.medicine.isNotBlank()) {
                        medicine.medicine.toString()
                    } else {
                        "登録されていない薬です。"
                    },
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}