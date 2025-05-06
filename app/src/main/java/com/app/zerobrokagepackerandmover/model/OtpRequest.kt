package com.app.zerobrokagepackerandmover.model

data class  OtpRequest(
    val mobile_number: String,
    val country_code: String,
    val otp: String
)