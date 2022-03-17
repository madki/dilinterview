package xyz.madki.dilmiltest.repos

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import xyz.madki.dilmiltest.api.MemesApi
import xyz.madki.dilmiltest.api.model.MemeApiModel
import xyz.madki.dilmiltest.api.model.MemesResponse
import xyz.madki.dilmiltest.db.MemesDao
import xyz.madki.dilmiltest.db.model.Meme
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemesRepository @Inject constructor(
    private val memesApi: MemesApi,
    private val memesDao: MemesDao
) {
    fun refresh(): Flow<RequestStatus> {
        return flow {
            emit(RequestStatus.LOADING)
            try {
                val result = memesApi.getMemes()
                if (result.isSuccessful) {
                    val body = result.body()
                    if (body?.success != true) {
                        emit(RequestStatus.ERROR)
                    } else {
                        memesDao.deleteAll()
                        memesDao.insertAll(
                            body.getMemes().map(MemeApiModel::toMeme)
                        )
                        emit(RequestStatus.DONE)
                    }
                } else {
                    emit(RequestStatus.ERROR)
                }
            } catch (ex: Exception) {
                emit(RequestStatus.ERROR)
            }
        }
    }

    fun getMemes(): Flow<List<Meme>> {
        return memesDao.getAll()
    }
}

fun MemeApiModel.toMeme(): Meme {
    return Meme(
        id,
        name,
        url,
        height,
        width
    )
}