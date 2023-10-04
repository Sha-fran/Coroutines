package com.example.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel:ViewModel() {
    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState:LiveData<UiState> = _uiState

    fun getData() {
        _uiState.value = UiState.Processing
        viewModelScope.launch {
            val result = CoroutinesWorker.executeWorks()
            _uiState.value = UiState.Result(result)
        }
    }
    sealed class UiState {
        object Empty:UiState()
        object Processing:UiState()
        class Result(val title:String):UiState()

    }
}
