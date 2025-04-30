package com.app.zerobrokagepackerandmover.repository

import com.app.zerobrokagepackerandmover.RetrofitInstance.RetrofitClient
import com.app.zerobrokagepackerandmover.RetrofitInstance.RetrofitInstance
import com.app.zerobrokagepackerandmover.model.BaseResponse
import com.app.zerobrokagepackerandmover.model.LoginRequest
import com.app.zerobrokagepackerandmover.model.OtpRequest
import retrofit2.Response

class BaseRepository {
    private val api = RetrofitClient.apiService

    suspend fun sendOtp(sendRequest: LoginRequest): Response<BaseResponse> {
        return api.sendOtp(sendRequest)
    }

    suspend fun verifyOtp(verifyRequest: OtpRequest): Response<BaseResponse> {
        return api.verifyOtp(verifyRequest)
    }
}