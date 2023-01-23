package com.example.iotmedicinenotebook.ui.detail.templates

import androidx.compose.foundation.layout.Column
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
import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.core.domain.toDateFormat

@Composable
fun DetailScreen(
    medicine: Medicine,
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
                    text = if (medicine.timeStamp != null) {
                        medicine.timeStamp.toDateFormat()
                    } else {
                        "日時情報がありません"
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (medicine.weight != null) {
                        medicine.weight.toString()
                    } else {
                        "正確に重量を測れませんでした"
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
}