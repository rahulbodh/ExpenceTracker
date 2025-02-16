package com.example.expencetracker.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.expencetracker.R
import com.example.expencetracker.data.repositories.ExpenseRepository

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(ExpenseRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.currentMonthExpenses.observe(viewLifecycleOwner) { expenses ->
            // Update UI with expenses
        }
        return binding.root
    }
}