package xyz.madki.dilmiltest.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.startWith
import xyz.madki.dilmiltest.db.model.Meme
import xyz.madki.dilmiltest.repos.MemesRepository
import xyz.madki.dilmiltest.repos.RequestStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMemesUseCase @Inject constructor(
    private val memesRepository: MemesRepository
) {
    private fun makeGetMemesRequest(): Flow<RequestStatus> {
        return memesRepository.refresh()
    }

    private fun getMemes(): Flow<List<Meme>> {
        return memesRepository.getMemes()
    }

    fun getMemesResult(): Flow<GetMemeResult> {
        return combine<RequestStatus, List<Meme>, GetMemeResult>(makeGetMemesRequest(), getMemes().onStart { emit(listOf()) }) { status, data ->
            GetMemeResult(status, data)
        }
    }

    data class GetMemeResult(
        val requestStatus: RequestStatus,
        val memes: List<Meme>
    )
}