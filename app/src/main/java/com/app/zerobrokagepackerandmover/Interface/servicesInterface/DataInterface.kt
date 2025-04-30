package com.app.zerobrokagepackerandmover.Interface.servicesInterface

import com.app.zerobrokagepackerandmover.model.BaseResponse
import com.app.zerobrokagepackerandmover.model.LoginRequest
import com.app.zerobrokagepackerandmover.model.OtpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DataInterface {

    @POST("sendloginOtp")
    suspend fun sendOtp(@Body request: LoginRequest): Response<BaseResponse>

    @POST("verifyOtp")
    suspend fun verifyOtp(@Body request: OtpRequest): Response<BaseResponse>
}