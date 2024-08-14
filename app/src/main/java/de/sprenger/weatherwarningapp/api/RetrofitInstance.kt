package de.sprenger.weatherwarningapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://warnung.bund.de/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val warningService: GetWeatherWarningService = retrofit.create(GetWeatherWarningService::class.java)
}