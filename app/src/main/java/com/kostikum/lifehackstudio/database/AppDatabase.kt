package com.kostikum.lifehackstudio.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kostikum.lifehackstudio.entities.Company
import com.kostikum.lifehackstudio.entities.CompanyDetails

@Database(entities = [Company::class, CompanyDetails::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val companiesDao: CompaniesDao
    abstract val companyDetailsDao: CompanyDetailsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "companies-db"
            ).build()
        }
    }
}
