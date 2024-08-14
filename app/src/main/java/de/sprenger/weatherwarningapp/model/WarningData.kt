package de.sprenger.weatherwarningapp.model

import com.google.gson.annotations.SerializedName

data class WarningData(
    val id: String,
    val version: Int,
    val startDate: String,
    val expiresDate: String?,
    val severity: String,
    val urgency: String,
    val type: String,
    @SerializedName("i18nTitle")
    val title: Title,
    val transKeys: TransKey?
)
