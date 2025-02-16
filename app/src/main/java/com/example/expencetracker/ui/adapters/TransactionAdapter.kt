package com.example.expencetracker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expencetracker.data.entities.Expense
import com.example.expencetracker.databinding.ItemTransactionBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionAdapter(
    private val onTransactionClick: (Expense) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var transactions = emptyList<Expense>()

    inner class TransactionViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense) {
            binding.apply {
                amountText.text = String.format("$%.2f", expense.amount)
                descriptionText.text = expense.description
                dateText.text = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    .format(Date(expense.date))
                root.setOnClickListener { onTransactionClick(expense) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount() = transactions.size

    fun submitList(newTransactions: List<Expense>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }
}