package com.example.market.present.ui.budget.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.market.domain.model.Budget
import com.example.market.present.theme.Surface06
import com.example.market.present.theme.Surface08
import com.example.market.present.theme.Surface09
import com.example.market.present.ui.budget.component.BudgetInfo
import com.example.market.present.ui.budget.component.CalculatedRow
import com.example.market.present.ui.budget.viewmodel.BudgetDailyViewModel
import java.time.LocalDate

@Preview
@Composable
fun PreviewBudgetDailyScreen() {
    BudgetDailyScreen()
}


@Composable
fun BudgetDailyRoute(
    viewModel: BudgetDailyViewModel = hiltViewModel(),
    selectedDate: LocalDate = LocalDate.now(),
) {
    viewModel.setSelectedDate(selectedDate)

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    BudgetDailyScreen(
        totalIncome = uiState.totalIncome,
        total = uiState.allIncome,
        budgetList = uiState.budgetList,
    )
}


@Composable
fun BudgetDailyScreen(
    modifier: Modifier = Modifier,
    totalIncome: Float = 0f,
    total: Float = 0f,
    budgetList: List<Budget> = listOf(),
) {
    Column(
        modifier = modifier
    ) {
        CalculatedRow(
            income = totalIncome.toInt(),
            expense = 0,
            total = total.toInt(),
            modifier = Modifier
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Surface09)
                .padding(
                    horizontal = 10.dp
                )
        ) {
            items(budgetList.size) { index ->
                BudgetInfo(
                    budgetId = budgetList[index].budgetId,
                    categoryName = budgetList[index].categoryName,
                    budget = budgetList[index].budget,
                    memo = budgetList[index].memo,
                    datetime = budgetList[index].inputDateTime,
                )
            }
        }
    }
}