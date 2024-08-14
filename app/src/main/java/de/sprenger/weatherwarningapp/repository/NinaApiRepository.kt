package de.sprenger.weatherwarningapp.repository

import de.sprenger.weatherwarningapp.api.GetWeatherWarningService
import de.sprenger.weatherwarningapp.model.WeatherWarningData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class NinaApiRepository(private val warningService: GetWeatherWarningService) {
    suspend fun getKatwarn(): List<WeatherWarningData>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<WeatherWarningData>> = warningService.getKatwarn().execute()

                if (response.isSuccessful) response.body()
                else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getBiwapp(): List<WeatherWarningData>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<WeatherWarningData>> = warningService.getBiwapp().execute()
                if (response.isSuccessful) response.body()
                else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getMowas(): List<WeatherWarningData>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<WeatherWarningData>> =
                    warningService.getMowas().execute()

                if (response.isSuccessful) response.body()
                else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getDwd(): List<WeatherWarningData>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<WeatherWarningData>> = warningService.getDwd().execute()

                if (response.isSuccessful) response.body()
                else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}