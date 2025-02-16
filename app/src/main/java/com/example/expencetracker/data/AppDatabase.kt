package com.example.expencetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expencetracker.data.dao.CategoryDao
import com.example.expencetracker.data.dao.ExpenseDao
import com.example.expencetracker.data.entities.Category
import com.example.expencetracker.data.entities.Expense

@Database(
    entities = [Category::class, Expense::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "expense_manager_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}