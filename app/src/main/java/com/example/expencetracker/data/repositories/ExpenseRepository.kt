package com.example.expencetracker.data.repositories

import com.example.expencetracker.data.dao.CategoryDao
import com.example.expencetracker.data.dao.ExpenseDao
import com.example.expencetracker.data.entities.Category
import com.example.expencetracker.data.entities.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao, private val categoryDao: CategoryDao) {
    // Category operations
    fun getCategoriesForMonth(monthYear: String) = categoryDao.getCategoriesForMonth(monthYear)

    suspend fun insertCategory(category: Category) = categoryDao.insert(category)

    suspend fun updateCategory(category: Category) = categoryDao.update(category)

    suspend fun deleteCategory(category: Category) = categoryDao.delete(category)

    // Expense operations
    fun getExpensesForDateRange(startDate: Long, endDate: Long) =
        expenseDao.getExpensesForDateRange(startDate, endDate)

    fun getExpensesByCategory(categoryId: Int) = expenseDao.getExpensesByCategory(categoryId)

    fun getTotalExpenseForCategory(categoryId: Int, startDate: Long, endDate: Long) =
        expenseDao.getTotalExpenseForCategory(categoryId, startDate, endDate)

    suspend fun insertExpense(expense: Expense) = expenseDao.insert(expense)

    suspend fun updateExpense(expense: Expense) = expenseDao.update(expense)

    suspend fun deleteExpense(expense: Expense) = expenseDao.delete(expense)
}