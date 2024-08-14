package de.sprenger.weatherwarningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import de.sprenger.weatherwarningapp.api.RetrofitInstance
import de.sprenger.weatherwarningapp.model.WeatherWarningData
import de.sprenger.weatherwarningapp.repository.NinaApiRepository
import de.sprenger.weatherwarningapp.ui.theme.WeatherWarningAppTheme
import de.sprenger.weatherwarningapp.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = NinaApiRepository(RetrofitInstance.warningService)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java).apply {
            this.repository = repository
        }

        setContent {
            WeatherWarningAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row {
                            Button(onClick = {  viewModel.fetchKatwarnWarnings() }) {
                                Text(text = "fetch Katwarn warnings")
                            }
                            Button(onClick = {  viewModel.fetchBiwappWarnings() }) {
                                Text(text = "fetch Biwapp warnings")
                            }
                        }
                        Row {
                            Button(onClick = {  viewModel.fetchMowasWarnings() }) {
                                Text(text = "fetch Mowas warnings")
                            }
                            Button(onClick = {  viewModel.fetchDwdWarnings() }) {
                                Text(text = "fetch DwD warnings")
                            }
                        }
                        WarningContainer(viewModel)
                    }
                }
            }
        }
        viewModel.fetchDwdWarnings()
    }
}

@Composable
fun WarningContainer(viewModel: WeatherViewModel) {
    val mapData = viewModel.warnings.observeAsState()
    val errorMessage = viewModel.errorMessage.observeAsState()

    mapData.value?.let { data ->
        for (weatherWarning: WeatherWarningData in data) {
            weatherWarning.title?.de?.let { Text(text = it, fontSize = 12.sp) }
        }
    }
    errorMessage.value?.let { error ->
        Text(text = error, color = MaterialTheme.colorScheme.error)
    }
}
