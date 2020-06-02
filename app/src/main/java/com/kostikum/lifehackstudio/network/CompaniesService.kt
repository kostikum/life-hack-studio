package com.kostikum.lifehackstudio.network

import com.kostikum.lifehackstudio.entities.Company
import com.kostikum.lifehackstudio.entities.CompanyDetails
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CompaniesService {

    @GET("test.php")
    fun getAllCompaniesAsync(): Deferred<List<Company>>

    @GET("test.php")
    fun getCompanyAsync(@Query("id") id: String): Deferred<List<CompanyDetails>>
}
