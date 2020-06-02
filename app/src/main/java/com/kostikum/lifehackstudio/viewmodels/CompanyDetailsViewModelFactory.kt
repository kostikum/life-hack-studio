package com.kostikum.lifehackstudio.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CompanyDetailsViewModelFactory(private val app: Application,
              private val companyId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompanyDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompanyDetailsViewModel(app, companyId) as T
        }
        throw IllegalArgumentException("Unable to construct view model!")
    }
}