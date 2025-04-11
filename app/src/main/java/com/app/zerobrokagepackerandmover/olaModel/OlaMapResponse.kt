package com.app.zerobrokagepackerandmover.olaModel

data class OlaMapResponse(
    val predictions: List<Prediction>
)

data class Prediction(
    val description: String
)