package com.example.market.present.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.domain.usecase.BudgetCategoryUseCase
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
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val budgetCategoryUseCase: BudgetCategoryUseCase
) : AndroidViewModel(application) {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            budgetCategoryUseCase.getAllCategory()
                .onStart {  }
                .onEach { categoryList ->
                    _uiState.update {
                        it.copy(
                            categoryList = categoryList
                        )
                    }
                }
                .onCompletion {
                    Log.e("TAG", "list: ${uiState.value.categoryList}", )
                }
                .catch {  }
                .launchIn(viewModelScope)
        }
    }

}
