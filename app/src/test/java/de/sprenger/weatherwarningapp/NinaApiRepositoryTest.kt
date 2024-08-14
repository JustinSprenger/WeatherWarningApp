package de.sprenger.weatherwarningapp

import de.sprenger.weatherwarningapp.api.GetWeatherWarningService
import de.sprenger.weatherwarningapp.model.Title
import de.sprenger.weatherwarningapp.model.WeatherWarningData
import de.sprenger.weatherwarningapp.repository.NinaApiRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

class NinaApiRepositoryTest {
    private val mockApiService = mock(GetWeatherWarningService::class.java)
    private val repository = NinaApiRepository(mockApiService)

    @Test
    fun `test successful data fetch`() = runBlocking {
        val mockData = listOf(
            WeatherWarningData(
                id = "dwdmap.2.49.0.0.276.0.DWD.PVW.1723584780000.b679bf4b-9ff9-4d10-9134-f0fdbfb0e41e.MUL",
                version = 5,
                startDate = "2024-08-13T23:33:00+02:00",
                expiresDate = "2024-08-13T23:33:00+02:00",
                severity = "Severe",
                urgency = "Immediate",
                type = "Alert",
                title = Title("Unit test"))
        )
//        val mockResponse = Response.success(mockData)
//        `when`(mockApiService.getDwd().execute()).thenReturn(mockResponse)

        println("MockApiService: $mockApiService")
        val response = mockApiService.getDwd().execute()
        println("Response: $response")

        val result = repository.getDwd()

        // Assert
        assertEquals(mockData, result)
    }

    @Test
    fun `test 500 error`() = runBlocking {
        val mockResponse: Response<List<WeatherWarningData>> = Response.error(500, okhttp3.ResponseBody.create(null, ""))
        `when`(mockApiService.getDwd().execute()).thenReturn(mockResponse)

        val result = repository.getDwd()

        // Assert
        assertNull(result)
    }
}