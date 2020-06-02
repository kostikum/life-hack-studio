package com.kostikum.lifehackstudio.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_details")
data class CompanyDetails(
    @PrimaryKey val id: String,
    val name: String,
    val img: String,
    val description: String,
    val lat: String,
    val lon: String,
    val www: String,
    val phone: String
)
