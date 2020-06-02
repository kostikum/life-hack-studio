package com.kostikum.lifehackstudio.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kostikum.lifehackstudio.entities.Company

@Dao
interface CompaniesDao {

    @Query("SELECT * FROM companies")
    fun getCompanies(): LiveData<List<Company>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(companies: List<Company>)
}