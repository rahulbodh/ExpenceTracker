package com.example.expencetracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.expencetracker.data.entities.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate")
    fun getExpensesForDateRange(startDate: Long, endDate: Long): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE categoryId = :categoryId")
    fun getExpensesByCategory(categoryId: Int): Flow<List<Expense>>

    @Query("SELECT SUM(amount) FROM expenses WHERE categoryId = :categoryId AND date BETWEEN :startDate AND :endDate")
    fun getTotalExpenseForCategory(categoryId: Int, startDate: Long, endDate: Long): Flow<Double?>

    @Insert
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)
}