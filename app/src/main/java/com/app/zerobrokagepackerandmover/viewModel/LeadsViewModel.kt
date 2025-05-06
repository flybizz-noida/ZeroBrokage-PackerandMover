package com.app.zerobrokagepackerandmover.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.zerobrokagepackerandmover.model.LeadsDetails
import com.app.zerobrokagepackerandmover.repository.BaseRepository
import kotlinx.coroutines.launch

class LeadsViewModel : ViewModel() {
    private val repository = BaseRepository()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _leadsBaseResponse = MutableLiveData<LeadsDetails>()
    val leadsBaseResponse: LiveData<LeadsDetails> get() = _leadsBaseResponse

    fun leadsDetails(userId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.leadsDetails(userId)
                if (response.isSuccessful && response.body() != null) {
                    _leadsBaseResponse.postValue(response.body())
                } else {
                    _errorMessage.postValue("Leads not available: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Error: ${e.localizedMessage}")
            }
        }
    }
}
