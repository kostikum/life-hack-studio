package com.kostikum.lifehackstudio.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kostikum.lifehackstudio.database.AppDatabase.Companion.getInstance
import com.kostikum.lifehackstudio.repository.CompaniesListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class CompaniesListViewModel(application: Application) : AndroidViewModel(application) {

    private val companiesRepository =
        CompaniesListRepository(
            getInstance(application)
        )
    val companiesList = companiesRepository.companies

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init { refreshDataFromRepository() }

    private fun refreshDataFromRepository() = viewModelScope.launch {
        try {
            companiesRepository.refreshCompanies()
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        } catch (networkError: IOException) {
            Log.e("error", networkError.toString())
            if (companiesList.value.isNullOrEmpty()) _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
