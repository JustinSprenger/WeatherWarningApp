package de.sprenger.weatherwarningapp

import de.sprenger.weatherwarningapp.api.GetWeatherWarningService
import de.sprenger.weatherwarningapp.model.WeatherWarningData
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NinaApiRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: GetWeatherWarningService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Die URL des MockWebServer
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GetWeatherWarningService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test getDwd success`() {
        val jsonResponse = """
            [
                {
                    "id": "biw.BIWAPP-83896",
                    "version": 5,
                    "startDate": "2024-08-13T10:45:42+02:00",
                    "expiresDate": "2024-08-14T10:26:00+02:00",
                    "severity": "Minor",
                    "urgency": "Unknown",
                    "type": "Update",
                    "i18nTitle": {
                        "de": "Hitzewarnung für den gesamten Landkreis Holzminden!"
                    }
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
            .setBody(jsonResponse)
            .setResponseCode(200))

        val call: Call<List<WeatherWarningData>> = service.getDwd()
        val response: Response<List<WeatherWarningData>> = call.execute()

        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        assertEquals(5, response.body()?.first()?.version ?: 0)
    }
}