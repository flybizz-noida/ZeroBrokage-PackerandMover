package com.app.zerobrokagepackerandmover.olaMapSdk

import com.app.zerobrokagepackerandmover.olaModel.OlaMapResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OlaMapApi {


    @Headers("X-Request-Id: 123456")
    @GET("v3/api/autosuggest")
    suspend fun getLocationSuggestions(
        @Query("query") query: String,
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double
    ): Response<OlaMapResponse>
}
