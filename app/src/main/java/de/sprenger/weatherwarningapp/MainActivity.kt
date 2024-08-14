package de.sprenger.weatherwarningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import de.sprenger.weatherwarningapp.api.GetWarningService
import de.sprenger.weatherwarningapp.api.RetrofitInstance
import de.sprenger.weatherwarningapp.model.WarningData
import de.sprenger.weatherwarningapp.repository.WarningRepository
import de.sprenger.weatherwarningapp.ui.theme.WeatherWarningAppTheme
import de.sprenger.weatherwarningapp.viewmodel.WarningViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: WarningViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = WarningRepository(RetrofitInstance.warningService)
        viewModel = ViewModelProvider(this).get(WarningViewModel::class.java).apply {
            this.repository = repository
        }

        setContent {

            WeatherWarningAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WarningContainer(viewModel)
                }
            }
        }
        viewModel.fetchDwdWarnings()
    }
}

@Composable
fun WarningContainer(viewModel: WarningViewModel) {
    val mapData = viewModel.warnings.observeAsState()
    val errorMessage = viewModel.errorMessage.observeAsState()

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Katwarn Map Data") }
//            )
//        },
//        content = { padding ->
//            Box(modifier = Modifier.padding(padding)) {
                mapData.value?.let { data ->
//                    MapDataList(mapData = data)
                    data[0].title.de?.let { Text(text = it) }
                }
                errorMessage.value?.let { error ->
                    Text(text = error, color = MaterialTheme.colorScheme.error)
                }
//            }
//        }
//    )
}
//
//@Composable
//fun MapDataList(mapData: List<WarningData>) {
//    LazyColumn(
//        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(mapData) { item ->
//            MapDataItem(item)
//        }
//    }
//}
//
//@Composable
//fun MapDataItem(mapData: MapData) {
//    Card(
//        elevation = 4.dp,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = mapData.title, style = MaterialTheme.typography.titleLarge)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = mapData.description, style = MaterialTheme.typography.bodyLarge)
//        }
//    }
//}
