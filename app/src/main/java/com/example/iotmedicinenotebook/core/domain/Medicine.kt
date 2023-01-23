package com.example.iotmedicinenotebook.core.domain

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


data class Medicine(
    val medicine: String = "読み取りに失敗しました",
    val weight : Double = 0.0,
    val timeStamp: Date = Date(),
) : Serializable

fun Date.toDateFormat(): String {
    val locale = Locale.JAPAN
    val sdf2 = SimpleDateFormat("HH:mm", locale)
    return sdf2.format(Date())
}