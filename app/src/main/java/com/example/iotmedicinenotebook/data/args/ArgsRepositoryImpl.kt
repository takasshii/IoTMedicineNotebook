package com.example.iotmedicinenotebook.data.args

import android.util.Log
import com.example.iotmedicinenotebook.core.Dispatcher
import com.example.iotmedicinenotebook.core.MedicineDispatcher
import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArgsRepositoryImpl @Inject constructor(
    @Dispatcher(MedicineDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : ArgsRepository {

    private val latestArgsMutex = Mutex()

    private var latestArgs: MedicineEntity? = null

    override suspend fun writeMedicineResultArgs(medicine: MedicineEntity): Result<Unit> =
        withContext(ioDispatcher) {
            repeat(5) {
                try {
                    latestArgsMutex.withLock {
                        latestArgs = medicine
                    }
                    Result.success(Unit)
                } catch (exception: Exception) {
                    Log.e("Args", exception.toString())
                }
            }
            Result.failure(IOException("引数の書き込みに失敗しました"))
        }

    override suspend fun getMedicineResultArgs(): Result<MedicineEntity> =
        withContext(Dispatchers.IO) {
            try {
                val args = requireNotNull(latestArgs) {
                    "引数が正しくセットされていません"
                }
                Result.success(args)
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
}
