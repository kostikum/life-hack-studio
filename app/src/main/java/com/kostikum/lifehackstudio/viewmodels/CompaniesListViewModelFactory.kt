package com.kostikum.lifehackstudio.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CompaniesListViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompaniesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompaniesListViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct view model!")
    }
}