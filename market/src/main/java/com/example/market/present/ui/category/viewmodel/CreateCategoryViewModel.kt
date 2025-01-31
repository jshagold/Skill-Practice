package com.example.market.present.ui.category.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.domain.usecase.BudgetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    application: Application,
    private val budgetCategoryUseCase: BudgetCategoryUseCase
) : AndroidViewModel(application) {

    fun createCategory(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetCategoryUseCase.createCategory(categoryName)
        }
    }

}