package xyz.madki.dilmiltest.ui.memes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import xyz.madki.dilmiltest.domain.GetMemesUseCase
import xyz.madki.dilmiltest.repos.RequestStatus
import javax.inject.Inject

@HiltViewModel
class MemesViewModel @Inject constructor(
    private val getMemesUseCase: GetMemesUseCase
): ViewModel() {
    val memes =
            getMemesUseCase.getMemesResult()
                .flowOn(Dispatchers.IO)
                .stateIn(
                    viewModelScope,
                    SharingStarted.Eagerly,
                    GetMemesUseCase.GetMemeResult(
                        RequestStatus.LOADING,
                        listOf())
                )

}