package com.example.iotmedicinenotebook.core

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


data class Medicine(
    val medicine: String? = null,
    val weight : Double? = null,
    val timeStamp: Date? = null,
) : Serializable

fun Date.toDateFormat(): String {
    val locale = Locale.JAPAN
    val sdf2 = SimpleDateFormat("HH:mm", locale)
    return sdf2.format(Date())
}