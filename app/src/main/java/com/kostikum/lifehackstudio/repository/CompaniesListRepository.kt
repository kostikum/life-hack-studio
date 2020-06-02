package com.kostikum.lifehackstudio.repository

import androidx.lifecycle.LiveData
import com.kostikum.lifehackstudio.database.AppDatabase
import com.kostikum.lifehackstudio.entities.Company
import com.kostikum.lifehackstudio.network.NetProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompaniesListRepository(private val database: AppDatabase) {

    val companies: LiveData<List<Company>> = database.companiesDao.getCompanies()

    suspend fun refreshCompanies() {
        withContext(Dispatchers.IO) {
            val companiesList = NetProvider.service.getAllCompaniesAsync().await()
            database.companiesDao.insertAll(companiesList)
        }
    }
}
