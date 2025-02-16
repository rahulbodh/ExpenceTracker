package com.example.expencetracker.ui.home

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.expencetracker.R

class HomeFragmentDirections private constructor() {
    data class ActionHomeToAddEditTransaction(val categoryId: Int) : NavDirections {
        override val actionId: Int
            get() = R.id.action_home_to_addEditTransaction

        override val arguments: Bundle
            get() {
                val args = Bundle()
                args.putInt("category_id", categoryId)
                return args
            }
    }

    companion object {
        fun actionHomeToAddEditTransaction(categoryId: Int): NavDirections =
            ActionHomeToAddEditTransaction(categoryId)
    }
}
