package com.kostikum.lifehackstudio.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CompaniesService {

    @GET("test_task/test.php")
    fun getAllCompaniesAsync(): Deferred<List<NetworkCompany>>

    @GET("test_task/test.php")
    fun getCompanyAsync(@Query("id") id: Int): Deferred<List<NetworkCompanyDetails>>
}
