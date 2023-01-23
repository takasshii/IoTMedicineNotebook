package com.example.iotmedicinenotebook.data

import android.util.Log
import com.example.iotmedicinenotebook.core.Medicine
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FireStoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : FireStoreRepository {

    override suspend fun fetchAllMedicineData(limit: Long): Result<List<Medicine>> =
        withContext(Dispatchers.IO) {
            fetchMedicineData(limit = limit)
        }

    private suspend fun fetchMedicineData(limit: Long): Result<List<Medicine>> {
        return suspendCoroutine { continuation ->
            firebaseFirestore.collection("users")
                .limit(limit)
                .get()
                .addOnSuccessListener { tasks ->
                    Log.d("RepositoryImpl", "Repository's result size is ${tasks.size()}")
                    val result = tasks.mapNotNull { it.toObject(Medicine::class.java) }
                    continuation.resume(Result.success(result))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result.failure(exception))
                }
        }
    }
}