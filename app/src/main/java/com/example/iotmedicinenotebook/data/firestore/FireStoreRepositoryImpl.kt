package com.example.iotmedicinenotebook.data.firestore

import android.util.Log
import com.example.iotmedicinenotebook.core.Dispatcher
import com.example.iotmedicinenotebook.core.MedicineDispatcher
import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FireStoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    @Dispatcher(MedicineDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : FireStoreRepository {

    override suspend fun fetchAllMedicineData(limit: Long): Result<List<Medicine>> =
        withContext(ioDispatcher) {
            fetchAllMedicine(limit = limit)
        }

    override suspend fun fetchCustomMedicineData(medicine: Medicine): Result<List<Medicine>> =
        withContext(ioDispatcher) {
            fetchNextLatestCustomMedicine(medicine)
        }

    override suspend fun fetchCustomMedicineDataList(medicine: MedicineEntity): Result<List<Medicine>> =
        withContext(ioDispatcher) {
            fetchNextLatestCustomMedicineList(medicine)
        }

    private suspend fun fetchAllMedicine(limit: Long): Result<List<Medicine>> {
        return suspendCoroutine { continuation ->
            firebaseFirestore.collection("users")
                .limit(limit)
                .get()
                .addOnSuccessListener { tasks ->
                    Log.d("UseCaseTest", "fetch All Medicine result is ${tasks.size()}")
                    val result = tasks.map { it.toObject(Medicine::class.java) }
                    continuation.resume(Result.success(result))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result.failure(exception))
                }
        }
    }

    private suspend fun fetchNextLatestCustomMedicine(medicine: Medicine): Result<List<Medicine>> {
        return suspendCoroutine { continuation ->
            medicine.timeStamp.let { timeStamp ->
                firebaseFirestore.collection("users")
                    .whereLessThan("timeStamp", timeStamp).limit(1)
                    .get()
                    .addOnSuccessListener { tasks ->
                        val result = tasks.map { it.toObject(Medicine::class.java) }
                        continuation.resume(Result.success(result))
                    }
                    .addOnFailureListener { exception ->
                        continuation.resume(Result.failure(exception))
                    }
            }
        }
    }

    private suspend fun fetchNextLatestCustomMedicineList(medicine: MedicineEntity): Result<List<Medicine>> {
        return suspendCoroutine { continuation ->
            medicine.timeStamp.let { timeStamp ->
                firebaseFirestore.collection("users")
                    .whereEqualTo("medicine", medicine.medicineName)
                    .whereLessThan("timeStamp", timeStamp)
                    .get()
                    .addOnSuccessListener { tasks ->
                        val result = tasks.map { it.toObject(Medicine::class.java) }
                        continuation.resume(Result.success(result))
                    }
                    .addOnFailureListener { exception ->
                        continuation.resume(Result.failure(exception))
                    }
            }
        }
    }
}