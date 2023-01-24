package com.example.iotmedicinenotebook.data.room.medicine

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicineDAO {
    @Insert
    fun insert(medicineList: List<MedicineEntity>)

    @Query("select * from medicine order by timeStamp desc")
    fun getAll(): List<MedicineEntity>

    @Query("select * from medicine WHERE medicineName = :name")
    fun getCustomMedicine(name : String): List<MedicineEntity>

    @Query("select * from medicine WHERE medicineName = :name order by timeStamp desc LIMIT 1")
    fun getLatestCustomMedicine(name : String): MedicineEntity

    @Query("select * from medicine order by timeStamp desc LIMIT 1")
    fun getLatestMedicine(): List<MedicineEntity>

    @Delete
    fun deleteSelected(medicine: MedicineEntity)
}