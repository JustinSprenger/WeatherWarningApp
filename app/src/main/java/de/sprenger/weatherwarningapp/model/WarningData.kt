package de.sprenger.weatherwarningapp.model

data class WarningData(
    val id: String,
    val version: Int,
    val startDate: String,
    val expiresDate: String?,
    val severity: String,
    val urgency: String,
    val type: String,
    val title: Title,
    val transKeys: TransKey?
)
