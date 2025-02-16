package com.example.expencetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.expencetracker.R
import com.example.expencetracker.data.entities.Expense
import com.example.expencetracker.databinding.FragmentAddEditTransactionBinding

class AddEditTransactionFragment : Fragment() {
    private lateinit var binding: FragmentAddEditTransactionBinding
    private val viewModel: TransactionViewModel by viewModels()
    private var categoryId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditTransactionBinding.inflate(inflater, container, false)

        // Get categoryId from arguments
        arguments?.let {
            categoryId = it.getInt("category_id")
        }

        setupViews()
        return binding.root
    }

    private fun setupViews() {
        binding.apply {
            saveButton.setOnClickListener {
                if (validateInputs()) {
                    saveTransaction()
                }
            }

            cancelButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val amount = binding.amountInput.text.toString()
        val description = binding.descriptionInput.text.toString()

        if (amount.isEmpty() || description.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveTransaction() {
        val amount = binding.amountInput.text.toString().toDouble()
        val description = binding.descriptionInput.text.toString()

        viewModel.insertExpense(
            Expense(
                categoryId = categoryId,
                amount = amount,
                description = description,
                date = System.currentTimeMillis()
            )
        )

        findNavController().navigateUp()
    }
}