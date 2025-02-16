package com.example.expencetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.expencetracker.data.entities.Expense
import com.example.expencetracker.data.repositories.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeViewModel(private val repository: ExpenseRepository) : ViewModel() {
    private val _currentMonthExpenses = MutableStateFlow<List<Expense>>(emptyList())
    val currentMonthExpenses: StateFlow<List<Expense>> = _currentMonthExpenses.asStateFlow()

    private val _totalExpense = MutableStateFlow(0.0)
    val totalExpense: StateFlow<Double> = _totalExpense.asStateFlow()

    init {
        loadCurrentMonthExpenses()
    }

    fun loadCurrentMonthExpenses() {
        viewModelScope.launch {
            val currentDate = Calendar.getInstance()
            val startOfMonth = currentDate.apply {
                set(Calendar.DAY_OF_MONTH, 1)
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis

            val endOfMonth = currentDate.apply {
                set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 59)
                set(Calendar.MILLISECOND, 999)
            }.timeInMillis

            repository.getExpensesForDateRange(startOfMonth, endOfMonth).collect { expenses ->
                _currentMonthExpenses.value = expenses
                _totalExpense.value = expenses.sumOf { it.amount }
            }
        }
    }

    private fun getCurrentMonthYear(): String {
        return SimpleDateFormat("MM-yyyy", Locale.getDefault()).format(Date())
    }
}

// Factory for creating HomeViewModel with proper dependency injection
class HomeViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}