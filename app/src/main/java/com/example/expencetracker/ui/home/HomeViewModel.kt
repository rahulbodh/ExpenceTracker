package com.example.expencetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expencetracker.data.entities.Expense
import com.example.expencetracker.data.repositories.ExpenseRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ExpenseRepository) : ViewModel() {
    private val _currentMonthExpenses = MutableLiveData<List<Expense>>()
    val currentMonthExpenses: LiveData<List<Expense>> = _currentMonthExpenses

    fun loadCurrentMonthExpenses() {
        viewModelScope.launch {
            val currentMonth = getCurrentMonthYear() // Implement this helper function
            repository.getExpensesForMonth(currentMonth).collect {
                _currentMonthExpenses.value = it
            }
        }
    }
}