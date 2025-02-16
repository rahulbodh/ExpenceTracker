package com.example.expencetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expencetracker.data.entities.Expense
import com.example.expencetracker.data.repositories.ExpenseRepository
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: ExpenseRepository) : ViewModel() {
    fun insertExpense(expense: Expense) = viewModelScope.launch {
        repository.insertExpense(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        repository.updateExpense(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repository.deleteExpense(expense)
    }
}