package de.sprenger.weatherwarningapp.api

import de.sprenger.weatherwarningapp.model.WeatherWarningData
import retrofit2.Call
import retrofit2.http.GET

interface GetWeatherWarningService {
    @GET("api31/katwarn/mapData.json")
    fun getKatwarn(): Call<List<WeatherWarningData>>
    @GET("api31/biwapp/mapData.json")
    fun getBiwapp(): Call<List<WeatherWarningData>>
    @GET("api31/mowas/mapData.json")
    fun getMowas(): Call<List<WeatherWarningData>>
    @GET("api31/dwd/mapData.json")
    fun getDwd(): Call<List<WeatherWarningData>>
}