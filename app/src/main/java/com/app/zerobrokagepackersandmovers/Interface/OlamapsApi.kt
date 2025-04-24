package com.app.zerobrokagepackersandmovers.Interface

import com.app.zerobrokagepackersandmovers.olaModel.OlaMapResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OlamapsApi {
    @Headers("X-Request-Id: 123456")
    @GET("places/v1/autocomplete")
    suspend fun getPlaces(
        @Query("input") input: String,
        @Query("api_key") apiKey: String
    ): Response<OlaMapResponse>
}
