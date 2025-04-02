package com.example.market.present.ui.budget.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.domain.model.Budget
import com.example.market.domain.usecase.BudgetUseCase
import com.example.market.present.ui.budget.state.BudgetDailyUiState
import com.example.market.present.ui.budget.state.BudgetUiState
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BudgetDailyViewModel @Inject constructor(
    application: Application,
    private val budgetUseCase: BudgetUseCase
) : AndroidViewModel(application) {

    private var _uiState: MutableStateFlow<BudgetDailyUiState> = MutableStateFlow(BudgetDailyUiState())
    val uiState: StateFlow<BudgetDailyUiState> = _uiState.asStateFlow()

    init {
        setSelectedDate(LocalDate.now())
    }

    fun setSelectedDate(date: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            val dateString = date.format(DateTimeFormatter.ofPattern("yyyyMM"))

            getTotalIncome()

            getTotalIncomeByMonth(dateString)
            getAllBudget(dateString)
        }
    }

    private fun getAllBudget(month: String) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetUseCase.getAllBudgetByMonthSortedByDay(month)
                .onStart {  }
                .onEach { budgetList ->
                    _uiState.update {
                        it.copy(
                            budgetList = budgetList
                        )
                    }
                }
                .onCompletion {  }
                .catch {  }
                .launchIn(viewModelScope)
        }
    }

    private fun getTotalIncome() {
        viewModelScope.launch(Dispatchers.IO) {
            budgetUseCase.getTotalIncome()
                .onStart {  }
                .onEach { totalIncome ->
                    _uiState.update {
                        it.copy(
                            allIncome = totalIncome
                        )
                    }
                }
                .onCompletion {  }
                .catch {  }
                .launchIn(viewModelScope)
        }
    }

    private fun getTotalIncomeByMonth(month: String) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetUseCase.getTotalIncomeByMonth(month)
                .onStart {  }
                .onEach { totalIncome ->
                    Log.e("TAG", "getTotalIncomeByMonth: totalincome $totalIncome", )
                    _uiState.update {
                        it.copy(
                            totalIncome = totalIncome
                        )
                    }
                }
                .onCompletion {  }
                .catch {  }
                .launchIn(viewModelScope)
        }
    }

}