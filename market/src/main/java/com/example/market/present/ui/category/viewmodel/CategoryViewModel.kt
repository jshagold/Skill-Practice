package com.example.market.present.ui.category.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.domain.usecase.BudgetCategoryUseCase
import com.example.market.present.ui.category.CategoryUiState
import com.example.market.present.utils.extension.move
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
class CategoryViewModel @Inject constructor(
    application: Application,
    private val budgetCategoryUseCase: BudgetCategoryUseCase
) : AndroidViewModel(application) {

    private val _uiState: MutableStateFlow<CategoryUiState> = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            budgetCategoryUseCase.getAllCategory()
                .onStart {  }
                .onEach { categoryList ->
                    _uiState.update {
                        it.copy(
                            categoryList = categoryList.sortedBy { budgetCategory ->  budgetCategory.displayIndex }
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

    fun deleteCategory(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetCategoryUseCase.deleteCategory(categoryId = categoryId)
        }
    }

    fun moveListIndex(from: Int, to: Int) {
        val list = uiState.value.categoryList.toMutableList()
        val beforeIndex = list[from].displayIndex
        list[from].displayIndex = list[to].displayIndex
        list[to].displayIndex = beforeIndex
        list.move(from, to)
        _uiState.update {
            it.copy(
                categoryList = list
            )
        }
    }

    fun saveListIndex() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("saveListIndex", "saveListIndex: ${uiState.value.categoryList}", )
            budgetCategoryUseCase.updateAllCategory(uiState.value.categoryList)
        }
    }
}