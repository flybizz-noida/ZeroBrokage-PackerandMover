package com.app.zerobrokagepackersandmovers.utils


import android.content.Context
import java.util.*

fun Context.setAppLocale(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val config = resources.configuration
    config.setLocale(locale)

    @Suppress("DEPRECATION")
    resources.updateConfiguration(config, resources.displayMetrics)
}
