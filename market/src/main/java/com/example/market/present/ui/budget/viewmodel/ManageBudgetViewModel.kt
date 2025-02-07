package com.example.market.present.ui.budget.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.domain.model.Budget
import com.example.market.domain.usecase.BudgetCategoryUseCase
import com.example.market.domain.usecase.BudgetUseCase
import com.example.market.present.ui.budget.state.ManageBudgetUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ManageBudgetViewModel @Inject constructor(
    application: Application,
    private val budgetUseCase: BudgetUseCase,
    private val categoryUseCase: BudgetCategoryUseCase,
) : AndroidViewModel(application) {

    private var _uiState: MutableStateFlow<ManageBudgetUiState> = MutableStateFlow(ManageBudgetUiState())
    val uiState: StateFlow<ManageBudgetUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllCategory()
            getAllBudget()
        }
    }


    private fun getAllCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUseCase.getAllCategory()
                .onStart {  }
                .onEach { categoryList ->
                    _uiState.update {
                        it.copy(
                            categoryList = categoryList
                        )
                    }
                }
                .onCompletion {  }
                .catch {  }
                .launchIn(viewModelScope)
        }
    }

    fun createBudget (categoryId: Int, budget: Float, memo: String, datetime: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val nowDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))

            val budgetDataClass = Budget(
                budgetId = 0,
                categoryId = categoryId,
                categoryName = "",
                budget = budget,
                memo = memo,
                dateTime = nowDateTime,
                inputDateTime = datetime.ifEmpty { nowDateTime }
            )

            budgetUseCase.inputBudget(budgetDataClass)
        }
    }

    fun deleteBudget(budgetId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetUseCase.deleteBudget(budgetId)
        }
    }


    private fun getAllBudget() {
        viewModelScope.launch(Dispatchers.IO) {
            budgetUseCase.getAllBudget()
                .onStart {  }
                .onEach { budgetList ->
                    _uiState.update {
                        it.copy(budgetList = budgetList)
                    }
                }
                .onCompletion {  }
                .catch {  }
                .launchIn(viewModelScope)
        }
    }
}