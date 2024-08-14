package de.sprenger.weatherwarningapp.repository

import de.sprenger.weatherwarningapp.api.GetWarningService
import de.sprenger.weatherwarningapp.api.RetrofitInstance
import de.sprenger.weatherwarningapp.model.WarningData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WarningRepository(private val warningService: GetWarningService) {
    suspend fun getKatwarn(): List<WarningData>? {
        return try {
            val response: Response<List<WarningData>> = warningService.getKatwarn().execute()

            if (response.isSuccessful) response.body()
            else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getBiwapp(): List<WarningData>? {
        return try {
            val response: Response<List<WarningData>> = warningService.getBiwapp().execute()
            if (response.isSuccessful) response.body()
            else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getMowas(): List<WarningData>? {
        return try {
            val response: Response<List<WarningData>> = warningService.getMowas().execute()

            if (response.isSuccessful) response.body()
            else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getDwd(): List<WarningData>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<WarningData>> = warningService.getDwd().execute()

                if (response.isSuccessful) response.body()
                else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}