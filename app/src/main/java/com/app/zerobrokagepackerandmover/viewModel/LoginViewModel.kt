package com.app.zerobrokagepackerandmover.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.zerobrokagepackerandmover.model.BaseResponse
import com.app.zerobrokagepackerandmover.model.LoginRequest
import com.app.zerobrokagepackerandmover.model.OtpRequest
import com.app.zerobrokagepackerandmover.repository.BaseRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val repository = BaseRepository()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loginBaseResponse = MutableLiveData<BaseResponse>()
    val loginBaseResponse: LiveData<BaseResponse> get() = _loginBaseResponse


    fun sendOtp(name: String, countryCode: String, number: String) {
        viewModelScope.launch {
            try {
                val response = repository.sendOtp(LoginRequest(name, countryCode, number))
                if (response.isSuccessful && response.body() != null) {
                    _loginBaseResponse.postValue(response.body())
                } else {
                    _errorMessage.postValue("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Error: ${e.localizedMessage}")
            }
        }
    }


    fun verifyOtp(mobileNumber: String, countryCode: String, otp: String) {
        viewModelScope.launch {
            try {
                val response = repository.verifyOtp(OtpRequest(mobileNumber, countryCode, otp))
                if (response.isSuccessful && response.body() != null) {
                    _loginBaseResponse.postValue(response.body())
                } else {
                    _errorMessage.postValue("Failed: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Error: ${e.localizedMessage}")
            }
        }
    }

}