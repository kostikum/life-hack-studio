package com.kostikum.lifehackstudio.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://megakohz.bget.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val service: CompaniesService = retrofit.create(CompaniesService::class.java)
}
