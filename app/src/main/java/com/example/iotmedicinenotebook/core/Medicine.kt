package com.example.iotmedicinenotebook.core

import java.io.Serializable
import java.sql.Timestamp

data class Medicine(
    val medicine: String? = null,
    val weight : Double? = null,
    val timestamp: Timestamp? = null,
) : Serializable