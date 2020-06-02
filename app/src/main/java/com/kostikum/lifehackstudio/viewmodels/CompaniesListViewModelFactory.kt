package com.kostikum.lifehackstudio.viewmodels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CompaniesListViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompaniesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompaniesListViewModel(activity.application) as T
        }
        throw IllegalArgumentException("Unable to construct view model!")
    }
}
