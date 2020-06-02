package com.kostikum.lifehackstudio.repository

import androidx.lifecycle.LiveData
import com.kostikum.lifehackstudio.database.AppDatabase
import com.kostikum.lifehackstudio.entities.CompanyDetails
import com.kostikum.lifehackstudio.network.NetProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompanyDetailsRepository(
    private val database: AppDatabase,
    private val id: String
) {
    val companyDetails: LiveData<CompanyDetails> = database.companyDetailsDao.getCompanyById(id)

    suspend fun refreshCompanyDetails() {
        withContext(Dispatchers.IO) {
            val company = NetProvider.service.getCompanyAsync(id).await()
            database.companyDetailsDao.insertCompany(company[0])
        }
    }
}
