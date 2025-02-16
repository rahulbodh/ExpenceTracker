package com.example.expencetracker.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expencetracker.data.AppDatabase
import com.example.expencetracker.data.entities.Category
import com.example.expencetracker.data.repositories.ExpenseRepository
import com.example.expencetracker.databinding.FragmentAddEditCategoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddEditCategoryFragment : Fragment() {
    private var _binding: FragmentAddEditCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels {
        CategoryViewModelFactory(
            ExpenseRepository(
                AppDatabase.getDatabase(requireContext()).expenseDao(),
                AppDatabase.getDatabase(requireContext()).categoryDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            saveButton.setOnClickListener {
                if (validateInputs()) {
                    saveCategory()
                }
            }

            cancelButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val name = binding.nameInput.text.toString()
        val budget = binding.budgetInput.text.toString()

        if (name.isEmpty() || budget.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        try {
            budget.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Please enter a valid budget amount", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun saveCategory() {
        val name = binding.nameInput.text.toString()
        val budget = binding.budgetInput.text.toString().toDouble()
        val monthYear = SimpleDateFormat("MM-yyyy", Locale.getDefault()).format(Date())

        val category = Category(
            name = name,
            budget = budget,
            monthYear = monthYear
        )

        viewModel.insertCategory(category)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
