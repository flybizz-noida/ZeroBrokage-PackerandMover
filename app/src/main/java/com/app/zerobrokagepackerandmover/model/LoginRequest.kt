package com.app.zerobrokagepackerandmover.model

data class LoginRequest(
    val name: String,
    val mobile_number: String,
    val country_code: String
)