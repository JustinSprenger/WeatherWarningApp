package de.sprenger.weatherwarningapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sprenger.weatherwarningapp.model.WarningData
import de.sprenger.weatherwarningapp.repository.WarningRepository
import kotlinx.coroutines.launch

class WarningViewModel: ViewModel() {
    lateinit var repository: WarningRepository

    private val _warnings = MutableLiveData<List<WarningData>>()
    val warnings: LiveData<List<WarningData>> get() = _warnings

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchKatwarnWarnings() {
        viewModelScope.launch {
            try {
                val warning = repository.getKatwarn()
                warning?.let {
                    _warnings.postValue(it)
                } ?: _errorMessage.postValue("error")
            } catch (e: Exception) {
                Log.d("Repository", "fetching error")
                _errorMessage.postValue("error")
            }
        }
    }

    fun fetchBiwappWarnings() {
        viewModelScope.launch {
            try {
                val warning = repository.getBiwapp()
                warning?.let {
                    _warnings.postValue(it)
                } ?: _errorMessage.postValue("error")
            } catch (e: Exception) {
                Log.d("Repository", "fetching error")
                _errorMessage.postValue("error")
            }
        }
    }

    fun fetchMowasWarnings() {
        viewModelScope.launch {
            try {
                val warning = repository.getMowas()
                warning?.let {
                    _warnings.postValue(it)
                } ?: _errorMessage.postValue("error")
            } catch (e: Exception) {
                Log.d("Repository", "fetching error")
                _errorMessage.postValue("error")
            }
        }
    }

    fun fetchDwdWarnings() {
        viewModelScope.launch {
            try {
                val warning = repository.getDwd()
                warning?.let {
                    _warnings.postValue(it)
                } ?: _errorMessage.postValue("error")
            } catch (e: Exception) {
                Log.d("Repository", "fetching error")
                _errorMessage.postValue("error")
            }
        }
    }
}