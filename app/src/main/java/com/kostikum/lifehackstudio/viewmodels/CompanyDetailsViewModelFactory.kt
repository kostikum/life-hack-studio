package com.kostikum.lifehackstudio.viewmodels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CompanyDetailsViewModelFactory(private val activity: Activity,
              private val companyId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompanyDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompanyDetailsViewModel(activity.application, companyId) as T
        }
        throw IllegalArgumentException("Unable to construct view model!")
    }
}
