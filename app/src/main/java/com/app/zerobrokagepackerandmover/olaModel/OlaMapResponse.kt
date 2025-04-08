package com.app.zerobrokagepackerandmover.olaModel

data class OlaMapResponse( val suggestions: List<Suggestion>
)

data class Suggestion(
    val name: String,
    val latitude: Double,
    val longitude: Double
)