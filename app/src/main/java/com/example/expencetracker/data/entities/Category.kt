package com.example.expencetracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
class Category (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val monthYear: String, // Format: "MM-yyyy"
    val budget: Double
)