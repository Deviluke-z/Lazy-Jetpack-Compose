package minhduc.deviluke.lazy.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import minhduc.deviluke.lazy.data.MarsPhoto
import minhduc.deviluke.lazy.network.RetrofitApiService

class MainViewModel: ViewModel() {
    var dataListResponse: List<MarsPhoto> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getData() {
        viewModelScope.launch {
            val retrofitApiService = RetrofitApiService.getInstance()
            try {
                val data = retrofitApiService.getMarsPhoto()
                dataListResponse = data
                Log.d("debug", data.toString())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("debug", errorMessage)
            }
        }
    }
}