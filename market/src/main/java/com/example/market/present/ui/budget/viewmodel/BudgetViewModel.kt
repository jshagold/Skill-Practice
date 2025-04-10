package com.example.market.present.ui.budget.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.domain.model.Budget
import com.example.market.domain.model.enums.BudgetDateType
import com.example.market.domain.usecase.BudgetUseCase
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
import javax.inject.Inject


@HiltViewModel
class BudgetViewModel @Inject constructor(
    application: Application,
    private val budgetUseCase: BudgetUseCase
) : AndroidViewModel(application) {

    private var _uiState: MutableStateFlow<BudgetUiState> = MutableStateFlow(BudgetUiState())
    val uiState: StateFlow<BudgetUiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            getAllBudget()
            getTotalIncome()
        }
    }


    /**
     *
     */

    fun selectDate(date: LocalDate) {
        _uiState.update {
            it.copy(
                selectedDate = date
            )
        }
    }

    fun setBeforeDate() {
        when(uiState.value.dateType) {
            BudgetDateType.YEAR -> {
                selectDate(uiState.value.selectedDate.minusYears(1))
            }
            BudgetDateType.MONTH -> {
                selectDate(uiState.value.selectedDate.minusMonths(1))
            }
            BudgetDateType.DAY -> {
                selectDate(uiState.value.selectedDate.minusDays(1))
            }
        }
    }

    fun setAfterDate() {
        when(uiState.value.dateType) {
            BudgetDateType.YEAR -> {
                selectDate(uiState.value.selectedDate.plusYears(1))
            }
            BudgetDateType.MONTH -> {
                selectDate(uiState.value.selectedDate.plusMonths(1))
            }
            BudgetDateType.DAY -> {
                selectDate(uiState.value.selectedDate.plusDays(1))
            }
        }
    }


    fun openCloseMonthPicker(value: Boolean) {
        _uiState.update {
            it.copy(
                isOpenMonthPicker = value
            )
        }
    }

    fun changeBudgetDateType(dateType: BudgetDateType) {
        _uiState.update {
            it.copy(
                dateType = dateType
            )
        }
    }


    /**
     *
     */


    private fun getAllBudget() {
        viewModelScope.launch(Dispatchers.IO) {
            budgetUseCase.getAllBudget()
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


    fun getTotalIncome() {
        viewModelScope.launch(Dispatchers.IO) {
            budgetUseCase.getTotalIncome()
                .onStart {  }
                .onEach { totalIncome ->
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