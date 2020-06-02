package com.kostikum.lifehackstudio.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kostikum.lifehackstudio.entities.CompanyDetails

@Dao
interface CompanyDetailsDao {

    @Query("SELECT * FROM company_details WHERE id = :companyId")
    fun getCompanyById(companyId: String): LiveData<CompanyDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompany(companyDetails: CompanyDetails)
}
