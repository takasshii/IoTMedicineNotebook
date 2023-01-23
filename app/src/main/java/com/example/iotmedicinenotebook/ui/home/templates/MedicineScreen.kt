package com.example.iotmedicinenotebook.ui.home.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iotmedicinenotebook.core.Medicine


@Composable
fun MedicineScreen(
    nextPage: (medicine: Medicine) -> Unit,
    medicineList: List<Medicine>,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier) {
        items(medicineList) { medicine ->
            MedicineButton(medicine, nextPage)
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
                text = medicine.timestamp.toString(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = medicine.medicine.toString(),
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}