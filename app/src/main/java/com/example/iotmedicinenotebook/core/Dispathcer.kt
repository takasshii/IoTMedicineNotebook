package com.example.iotmedicinenotebook.core

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val medicineDispatcher: MedicineDispatcher)

enum class MedicineDispatcher {
    IO
}