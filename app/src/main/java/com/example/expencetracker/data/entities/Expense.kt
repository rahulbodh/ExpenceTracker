package com.example.expencetracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,
    val amount: Double,
    val description: String,
    val date: Long
)