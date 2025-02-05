package com.example.market.present.ui.budget.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.market.domain.usecase.BudgetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageBudgetViewModel @Inject constructor(
    application: Application,
    private val budgetUseCase: BudgetUseCase
) : AndroidViewModel(application) {

    create
}