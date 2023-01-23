package com.example.iotmedicinenotebook.data.firestore

import com.example.iotmedicinenotebook.core.Dispatcher
import com.example.iotmedicinenotebook.core.MedicineDispatcher
import com.example.iotmedicinenotebook.core.domain.Medicine
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
            fetchMedicineData(limit = limit)
        }

    private suspend fun fetchMedicineData(limit: Long): Result<List<Medicine>> {
        return suspendCoroutine { continuation ->
            firebaseFirestore.collection("users")
                .limit(limit)
                .get()
                .addOnSuccessListener { tasks ->
                    val result = tasks.mapNotNull { it.toObject(Medicine::class.java) }
                    continuation.resume(Result.success(result))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result.failure(exception))
                }
        }
    }
}