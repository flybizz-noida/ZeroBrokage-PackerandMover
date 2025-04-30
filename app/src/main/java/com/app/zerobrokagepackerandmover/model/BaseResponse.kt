package com.app.zerobrokagepackerandmover.model


data class BaseResponse(
    val success: Boolean,
    val message: String,
    val `data`: ResponseData
)

data class ResponseData(
    val user_id: Int,
    val name: String,
    val mobile_number: String,
    val country_code :String,
    val verify_otp: Boolean,
    val otp: Int,
    val company_name: String,
    val success: Boolean,
    val otp_expires_at: String
)