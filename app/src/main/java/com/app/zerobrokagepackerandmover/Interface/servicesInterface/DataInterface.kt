package com.app.zerobrokagepackerandmover.Interface.servicesInterface

import com.app.zerobrokagepackerandmover.model.BaseResponse
import com.app.zerobrokagepackerandmover.model.LeadsDetails
import com.app.zerobrokagepackerandmover.model.LoginRequest
import com.app.zerobrokagepackerandmover.model.OtpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DataInterface {

    @POST("sendloginOtp")
    suspend fun sendOtp(@Body request: LoginRequest): Response<BaseResponse>

    @POST("verifyOtp")
    suspend fun verifyOtp(@Body request: OtpRequest): Response<BaseResponse>

    @GET("getOrder/userId")
    suspend fun leadsDetails(@Path ("userId")  userId:Int) : Response<LeadsDetails>

}