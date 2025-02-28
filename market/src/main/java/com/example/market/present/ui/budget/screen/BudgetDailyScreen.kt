package com.example.market.present.ui.budget.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.market.domain.model.Budget
import com.example.market.present.ui.budget.component.BudgetInfo
import com.example.market.present.ui.budget.viewmodel.BudgetDailyViewModel

@Preview
@Composable
fun PreviewBudgetDailyScreen() {
    BudgetDailyScreen()
}


@Composable
fun BudgetDailyRoute(
    viewModel: BudgetDailyViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BudgetDailyScreen(
        total = uiState.totalIncome,
        balance = uiState.balance,
        budgetList = uiState.budgetList,
    )
}


@Composable
fun BudgetDailyScreen(
    modifier: Modifier = Modifier,
    total: Float = 0f,
    balance: Float = 0f,
    budgetList: List<Budget> = listOf(),
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Budget"
        )
        Text(
            text = "total : $total"
        )
        Text(
            text = "balance : $balance"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
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