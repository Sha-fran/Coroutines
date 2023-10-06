package com.example.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel:ViewModel() {
    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState:LiveData<UiState> = _uiState
    val repo = MyApplication.getApp().repo

    fun getData() {
        _uiState.value = UiState.Processing

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bitcoinResponse = repo.getCurrencyByName("bitcoin")

                if (bitcoinResponse.isSuccessful) {
                    val data = bitcoinResponse.body()?.data
                    _uiState.postValue(UiState.Result("${data?.id} ${data?.rateUsd}\n"))
                } else {
                    _uiState.postValue(UiState.Error("Error code ${bitcoinResponse.code()}"))
                }
            } catch (e:Exception) {
                _uiState.postValue(UiState.Error(e.localizedMessage))
            }

//            var result = ""
//            val bitcoin = async { repo.getCurrencyByName("bitcoin") }.await()
//            val ounce = async { repo.getCurrencyByName("silver-ounce") }.await()
//            val dash = async { repo.getCurrencyByName("dash") }.await()
//            val binance = async { repo.getCurrencyByName("binance-coin") }.await()

//            if (bitcoin.data != null) {
//                result = "${bitcoin.data.id} ${bitcoin.data.rateUsd}\n"
//            }

//            if (ounce.data != null) {
//                result = "${ounce.data.id} ${ounce.data.rateUsd}\n"
//            }
//
//            if (dash.data != null) {
//                result = "${dash.data.id} ${dash.data.rateUsd}\n"
//            }
//
//            if (binance.data != null) {
//                result = "${binance.data.id} ${binance.data.rateUsd}"
//            }



//            withContext(Dispatchers.Main) {
//                _uiState.value = (UiState.Result(result))
//            }


//            var response:BitcoinResponce
//
//            withContext(Dispatchers.IO) {
//                response = repo.getCurrencyByName("bitcoin")
//            }
//
//            if (response.data != null) {
//                _uiState.value = UiState.Result("${response.data?.id} ${response.data?.rateUsd}")
//            } else {
//                _uiState.value = UiState.Error("Error")
//            }
        }
    }

    sealed class UiState {
        object Empty:UiState()
        object Processing:UiState()
        class Result(val title:String):UiState()
        class Error(val description:String):UiState()

    }
}
